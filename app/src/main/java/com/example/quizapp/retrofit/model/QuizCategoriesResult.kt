package com.example.quizapp.retrofit.model

import com.google.gson.annotations.SerializedName

data class QuizCategoriesResult(
    @SerializedName("trivia_categories")
    val categories: List<QuizCategory>
)