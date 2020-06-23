package com.example.app_events_around.domain.model

import java.io.Serializable

data class Event(
    val people: List<People>,
    val date: Long,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String,
    val id: String,
    val cupons: List<Cupons>
) : Serializable