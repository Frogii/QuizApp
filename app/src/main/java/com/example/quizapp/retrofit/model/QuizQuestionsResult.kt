package com.example.quizapp.retrofit.model

data class QuizQuestionsResult(
    val response_code: Int,
    val results: List<QuizQuestion>
)