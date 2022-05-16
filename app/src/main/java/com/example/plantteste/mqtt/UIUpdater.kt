package com.example.plantteste.mqtt

interface UIUpdater {
    fun resetUIWithConnection(status: Boolean)
    fun updateStatusViewWith(status: String)
    fun update(message: String)
}