package com.example.plantteste.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.plantteste.databinding.ActivityDetailsBinding
import com.example.plantteste.mqtt.MQTTConnectionParams
import com.example.plantteste.mqtt.MQTTmanager

class DetailsActivity: AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailsBinding
    private var mqttManager: MQTTmanager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val connectionParams = MQTTConnectionParams()
        mqttManager = MQTTmanager(connectionParams,applicationContext)
        mqttManager?.connect()


        with(binding) {
            buttonIrrigar.setOnClickListener(this@DetailsActivity)
            switchAutoIrrigar.setOnClickListener(this@DetailsActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({ switchAutoIrrigarClick() },2000)
    }

    private fun send(message: String) {
        mqttManager?.publish(message)
    }

    override fun onClick(p0: View?) {
        with(binding){
            when(p0) {
                buttonIrrigar -> buttonIrrigarClick()
                switchAutoIrrigar -> switchAutoIrrigarClick()
                else -> {}
            }
        }
    }

    private fun switchAutoIrrigarClick() {
        if (binding.switchAutoIrrigar.isChecked) {
            send("AUTO")
        } else {
            send("MANUAL")
        }
    }

    private fun buttonIrrigarClick() {
        send("ON")
        Handler(Looper.getMainLooper()).postDelayed({ send("OFF") },2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        mqttManager?.disconnect()
    }
}