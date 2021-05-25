package com.example.quizapp.ui.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.room.model.QuizTry
import com.example.quizapp.util.QuizDifficulty
import kotlinx.coroutines.launch

class ScoreViewModel(val quizRepository: QuizRepository) : ViewModel() {

    private fun insertAttempt(quizTry: QuizTry) {
        viewModelScope.launch {
            quizRepository.insertAttempt(quizTry)
        }
    }

    fun addAttempt(category: String, rightAnswers: Int, date: String, difficulty: String) {
        val quizTry = QuizTry(category, rightAnswers, date, difficulty)
        insertAttempt(quizTry)
    }

}