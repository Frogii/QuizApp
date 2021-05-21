package com.example.quizapp.ui.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.room.model.QuizTry
import kotlinx.coroutines.launch

class ScoreViewModel(val quizRepository: QuizRepository) : ViewModel() {

    private fun insertAttempt(quizTry: QuizTry) {
        viewModelScope.launch {
            quizRepository.insertAttempt(quizTry)
        }
    }

    fun addAttempt(quizTry: QuizTry) {
        insertAttempt(quizTry)
    }

}