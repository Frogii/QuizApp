package com.example.quizapp.ui.questions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.retrofit.model.QuizCategory
import com.example.quizapp.retrofit.model.QuizQuestion
import com.example.quizapp.util.ApiStatus
import kotlinx.coroutines.launch

class QuestionsViewModel(private val quizRepository: QuizRepository, category: QuizCategory) :
    ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _category = MutableLiveData<QuizCategory>()
    val category: LiveData<QuizCategory>
        get() = _category

    private val _questions = MutableLiveData<List<QuizQuestion>>()
    val questions: LiveData<List<QuizQuestion>>
        get() = _questions

    init {
        _category.value = category
        getQuestions()
    }

    private fun getQuestions() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                category.value?.id?.let { id ->
                    val response = quizRepository.getQuestions(id)
                    if (response.isSuccessful) {
                        _questions.value = response.body()?.results
                        if (response.body()?.results?.isEmpty() == true) {
                            _status.value = ApiStatus.EMPTY
                        } else {
                            _status.value = ApiStatus.DONE
                        }
                    }
                    Log.d("myLog", "id - $id")
                    Log.d("myLog", "questions - ${questions.value.toString()}")
                }
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                Log.d("myLog", e.message.toString())
            }
        }
    }
}