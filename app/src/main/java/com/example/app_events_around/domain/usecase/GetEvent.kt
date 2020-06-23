package com.example.app_events_around.domain.usecase

import com.example.app_events_around.domain.contract.EventsContract

class GetEvent(private val repository: EventsContract) {

    suspend operator fun invoke(eventId: String) = repository.getEvent(eventId)

}