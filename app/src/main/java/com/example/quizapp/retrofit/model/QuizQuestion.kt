package com.example.quizapp.retrofit.model

import androidx.core.text.HtmlCompat

data class QuizQuestion(
    val category: String,
    val correct_answer: String,
    val difficulty: String,
    val incorrect_answers: List<String>,
    val question: String,
    val type: String
) {
    val formattedQuestion: String
        get() = HtmlCompat.fromHtml(question, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
}

