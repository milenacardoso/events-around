package com.example.app_events_around.common.extensions

import java.text.NumberFormat
import java.util.*

fun Double.convertToPtBrCurrency(): String {
    val ptBr = Locale("pt", "BR")
    return NumberFormat.getCurrencyInstance(ptBr).format(this)
}