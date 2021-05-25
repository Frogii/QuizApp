package com.example.quizapp.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quizapp.util.QuizDifficulty

@Entity(tableName = "quiz_history")
data class QuizTry(
    val category: String,
    val rightAnswers: Int,
    val date: String,
    val difficulty: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}