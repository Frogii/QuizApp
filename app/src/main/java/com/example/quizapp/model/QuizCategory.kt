package com.example.quizapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuizCategory(
    val id: Int,
    val name: String
) : Parcelable {
}