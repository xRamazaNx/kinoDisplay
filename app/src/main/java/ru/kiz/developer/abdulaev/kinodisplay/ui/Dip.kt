package ru.kiz.developer.abdulaev.kinodisplay.ui

import android.content.Context

fun Context.dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()