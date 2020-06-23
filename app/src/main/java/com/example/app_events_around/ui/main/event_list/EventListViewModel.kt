package com.example.app_events_around.ui.main.event_list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_events_around.common.base.BaseState
import com.example.app_events_around.domain.model.Event
import com.example.app_events_around.domain.usecase.GetEvents
import kotlinx.coroutines.launch

class EventListViewModel(private val getEvents: GetEvents) : ViewModel() {

    val stateList = MutableLiveData<BaseState<List<Event>>>().apply {
        value = BaseState.Loading
    }

    fun loadEventList() {
        viewModelScope.launch {
            try {
                val eventList = getEvents.invoke()
                if (eventList.isNullOrEmpty()) stateList.value = BaseState.Empty
                else stateList.value = BaseState.Success(eventList)
            } catch (ex: Exception) {
                stateList.value = BaseState.Failed(ex)
            }
        }
    }
}
