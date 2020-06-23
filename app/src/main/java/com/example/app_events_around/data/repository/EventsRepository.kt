package com.example.app_events_around.data.repository

import com.example.app_events_around.BuildConfig
import com.example.app_events_around.data.api.EventsService
import com.example.app_events_around.domain.contract.EventsContract
import com.example.app_events_around.domain.model.Checkin
import com.example.app_events_around.domain.model.Event
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventsRepository : EventsContract {

    private val retrofitServer by lazy<EventsService> {
        val service = EventsService::class.java

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build().create(service)
    }

    override suspend fun getEvents(): List<Event> = retrofitServer.getEvents()

    override suspend fun getEvent(eventId: String) = retrofitServer.getEvent(eventId)

    override suspend fun setCheckin(checkin: Checkin) = retrofitServer.setCheckin(checkin)
}