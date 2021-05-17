package com.example.quizapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.retrofit.model.QuizCategory
import kotlinx.coroutines.launch

class CategoriesViewModel(private val quizRepository: QuizRepository) : ViewModel() {

    private val _categories = MutableLiveData<List<QuizCategory>>()
    val categories: LiveData<List<QuizCategory>>
        get() = _categories

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            try {
                val response = quizRepository.getCategories()
                if (response.isSuccessful) {
                    _categories.value = response.body()?.categories
                    Log.d("myLog", response.body().toString())
                }
            } catch (e: Exception) {
                Log.d("myLog", e.message.toString())
            }
        }
    }
}