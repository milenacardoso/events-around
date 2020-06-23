package com.example.app_events_around.common.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun Context.showToast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    return Toast.makeText(
        this,
        text,
        duration
    ).show()
}