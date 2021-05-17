package com.example.quizapp.model

data class QuizHistory(
    val id: Int,
    val categoryName: String,
    val rightAnswers: Int,
    val date: Long
) {
}