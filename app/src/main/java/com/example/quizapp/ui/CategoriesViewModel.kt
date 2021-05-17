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

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    val categories: LiveData<List<QuizCategory>>
        get() = quizRepository.categories

    init {
        getCategories()
    }

    private fun getCategories() {
        if(categories.value.isNullOrEmpty()) {
            viewModelScope.launch {
                _status.value = ApiStatus.LOADING
                try {
                    quizRepository.getCategories()
                    _status.value = ApiStatus.DONE
                } catch (e: Exception) {
                    _status.value = ApiStatus.ERROR
                    Log.d("myLog", e.message.toString())
                }
            }
        }
    }
}

enum class ApiStatus { LOADING, ERROR, DONE }