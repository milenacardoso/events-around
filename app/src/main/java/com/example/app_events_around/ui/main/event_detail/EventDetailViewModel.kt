package com.example.app_events_around.ui.main.event_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_events_around.common.base.BaseState
import com.example.app_events_around.domain.model.Checkin
import com.example.app_events_around.domain.model.Event
import com.example.app_events_around.domain.usecase.GetEvent
import com.example.app_events_around.domain.usecase.SetCheckin
import kotlinx.coroutines.launch

class EventDetailViewModel(private val getEvent: GetEvent, private val setCheckin: SetCheckin) :
    ViewModel() {

    val stateDetail = MutableLiveData<BaseState<Event>>().apply {
        value = BaseState.Loading
    }
    val stateCheckin = MutableLiveData<BaseState<String>>()

    private val _data = MutableLiveData<Event>()
    val data: LiveData<Event>
        get() = _data

    fun loadEventDetail(eventId: String) {
        viewModelScope.launch {
            try {
                val event = getEvent.invoke(eventId)
                if (event != null) stateDetail.value = BaseState.Success(event)
                else stateDetail.value = BaseState.Empty
            } catch (ex: Exception) {
                stateDetail.value = BaseState.Failed(ex)
            }
        }
    }

    fun setEventCheckin(eventId: String, name: String, email: String) {
        val checkin = Checkin(eventId, name, email)
        viewModelScope.launch {
            try {
                val result = setCheckin.invoke(checkin)
                stateCheckin.value = BaseState.Success(result)
            } catch (ex: Exception) {
                stateCheckin.value = BaseState.Failed(ex)
            }
        }
    }
}
