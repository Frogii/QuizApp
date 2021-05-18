package com.example.quizapp.retrofit.model

import androidx.core.text.HtmlCompat
import com.google.gson.annotations.SerializedName

data class QuizQuestion(
    val category: String,
    @SerializedName("correct_answer")
    val answer: String,
    val difficulty: String,
    val question: String,
    val type: String
) {
    val formattedQuestion: String
        get() = HtmlCompat.fromHtml(question, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()

    val correctAnswer: Boolean
        get() = answer == "True"
}

