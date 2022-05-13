package com.example.plantteste.mqtt

data class MQTTConnectionParams(
    val clientId:String,
    val host: String,
    val topic: String,
    val username: String,
    val password: String)

