package com.example.app_events_around.common.base

sealed class BaseState<out T> {
    object Loading : BaseState<Nothing>()
    data class Success<T>(val data: T) : BaseState<T>()
    object Empty : BaseState<Nothing>()
    data class Failed(val throwable: Throwable) : BaseState<Nothing>()
}