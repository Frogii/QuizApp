package com.example.quizapp.retrofit.model

data class QuizQuestion(
    val category: String,
    val correct_answer: String,
    val difficulty: String,
    val incorrect_answers: List<String>,
    val question: String,
    val type: String
)