package com.example.app_events_around.domain.usecase

import com.example.app_events_around.domain.contract.EventsContract

class GetEvents(private val repository: EventsContract) {

    suspend operator fun invoke() = repository.getEvents()

}