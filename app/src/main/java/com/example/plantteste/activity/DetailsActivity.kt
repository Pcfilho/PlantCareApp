package com.example.plantteste.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.plantteste.mqtt.MQTTConnectionParams
import com.example.plantteste.mqtt.MQTTmanager

class DetailsActivity: AppCompatActivity() {

    private var mqttManager: MQTTmanager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val host = "tcp://broker.hivemq.com:1883"
        val topic = "plantcare2022"
        val connectionParams = MQTTConnectionParams("MQTTSample",host,topic,"","")
        mqttManager = MQTTmanager(connectionParams,applicationContext)
        mqttManager?.connect()
    }
}