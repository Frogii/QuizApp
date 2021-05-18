package com.example.quizapp.retrofit.model

data class QuizQuestions(
    val response_code: Int,
    val results: List<QuizQuestion>
)