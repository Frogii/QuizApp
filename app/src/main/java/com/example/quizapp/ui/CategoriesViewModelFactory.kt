package com.example.quizapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.repository.QuizRepository

class CategoriesViewModelFactory(private val quizRepository: QuizRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoriesViewModel(quizRepository) as T
    }
}