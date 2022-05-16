package com.example.plantteste.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.plantteste.databinding.ActivityDetailsBinding
import com.example.plantteste.mqtt.MQTTConnectionParams
import com.example.plantteste.mqtt.MQTTmanager
import com.example.plantteste.mqtt.UIUpdater

class DetailsActivity: AppCompatActivity(), View.OnClickListener, UIUpdater {

    private lateinit var binding: ActivityDetailsBinding
    private var mqttManager: MQTTmanager? = null
    private var text = ""

    override fun resetUIWithConnection(status: Boolean) {
        if (status) {
            updateStatusViewWith("Connected")
        } else {
            updateStatusViewWith("Disconnected")
        }
    }

    override fun updateStatusViewWith(status: String) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
    }

    override fun update(message: String) {
        text = message
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonIrrigar.setOnClickListener {
            sendMessage("ON")
            Handler(Looper.getMainLooper()).postDelayed({ sendMessage("OFF") },1000)
        }

        binding.switchAutomacao.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()


        val host = "tcp://broker.hivemq.com:1883"
        val topic = "plantcare2022"
        val connectionParams = MQTTConnectionParams("MQTTSample",host,topic,"","")
        mqttManager = MQTTmanager(connectionParams, applicationContext, this)
        mqttManager?.connect()
    }

    fun sendMessage(message: String) {
        mqttManager?.publish(message)
    }

    override fun onClick(v: View?) {
        if (v == binding.switchAutomacao) {
            if (text.startsWith("E")) {
                val command = text.substring(0, 1)
                if (command == "ON") {
                    sendMessage("ON")
                }
                else {
                    sendMessage("OFF")
                }
            }
        }
    }


}