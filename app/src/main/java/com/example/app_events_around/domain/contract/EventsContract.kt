package com.example.app_events_around.domain.contract

import com.example.app_events_around.domain.model.Checkin
import com.example.app_events_around.domain.model.Event

interface EventsContract {

    suspend fun getEvents(): List<Event>
    suspend fun getEvent(eventId: String): Event?
    suspend fun setCheckin(checkin: Checkin): String

}