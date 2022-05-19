package com.example.plantteste.mqtt

import com.example.plantteste.mqtt.MqttConstants.CLIENT_ID
import com.example.plantteste.mqtt.MqttConstants.SERVER_IP
import com.example.plantteste.mqtt.MqttConstants.TOPIC

data class MQTTConnectionParams(
    val clientId:String = CLIENT_ID,
    val host: String = SERVER_IP,
    val topic: String = TOPIC,
)

