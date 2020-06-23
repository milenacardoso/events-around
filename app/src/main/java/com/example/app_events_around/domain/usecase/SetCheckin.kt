package com.example.app_events_around.domain.usecase

import com.example.app_events_around.domain.contract.EventsContract
import com.example.app_events_around.domain.model.Checkin

class SetCheckin(private val repository: EventsContract) {

    suspend operator fun invoke(checkin: Checkin) = repository.setCheckin(checkin)

}