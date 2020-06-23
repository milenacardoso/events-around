package com.example.app_events_around.data.api

import com.example.app_events_around.domain.model.Checkin
import com.example.app_events_around.domain.model.Event
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventsService {

    @GET("events")
    suspend fun getEvents(
    ): List<Event>

    @GET("events/{eventId}")
    suspend fun getEvent(
        @Path("eventId") eventId: String
    ): Event

    @POST("checkin")
    suspend fun setCheckin(
        @Body checkin: Checkin
    ): String

}