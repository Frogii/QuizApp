package com.example.quizapp.util

import java.text.SimpleDateFormat
import java.util.*

class AppDateUtils {

    companion object {
        fun getCurrentDay(): String {
            val dateFormatter = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
            return dateFormatter.format(Date())
        }
    }
}