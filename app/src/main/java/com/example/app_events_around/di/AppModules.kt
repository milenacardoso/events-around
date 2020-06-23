package com.example.app_events_around.di

import com.example.app_events_around.data.repository.EventsRepository
import com.example.app_events_around.domain.contract.EventsContract
import com.example.app_events_around.domain.usecase.GetEvent
import com.example.app_events_around.domain.usecase.GetEvents
import com.example.app_events_around.domain.usecase.SetCheckin
import com.example.app_events_around.ui.main.event_detail.EventDetailViewModel
import com.example.app_events_around.ui.main.event_list.EventListAdapter
import com.example.app_events_around.ui.main.event_list.EventListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

private val dataModule = module {
    single<EventsContract> { EventsRepository() }
}

private val domainModule = module {
    factory { GetEvents(get()) }
    factory { GetEvent(get()) }
    factory { SetCheckin(get()) }
}

private val viewModelModule = module {
    factory { (click: EventListAdapter.Callback) ->
        EventListAdapter(
            click
        )
    }
    viewModel {
        EventListViewModel(
            get()
        )
    }
    viewModel {
        EventDetailViewModel(
            get(),
            get()
        )
    }
}

val appModules: List<Module> = listOf(dataModule, domainModule, viewModelModule)
