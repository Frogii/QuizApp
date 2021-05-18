package com.example.quizapp.ui.questions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.retrofit.model.QuizCategory

class QuestionsViewModelFactory(private val quizRepository: QuizRepository, private val category: QuizCategory) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuestionsViewModel(quizRepository, category) as T
    }
}