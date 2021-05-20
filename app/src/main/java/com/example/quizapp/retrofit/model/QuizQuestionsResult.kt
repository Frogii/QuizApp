package com.example.quizapp.retrofit.model

import com.google.gson.annotations.SerializedName

data class QuizQuestionsResult(
    @SerializedName("response_code")
    val responseCode: Int,
    val results: List<QuizQuestion>
)