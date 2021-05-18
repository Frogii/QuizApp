package com.example.quizapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quizapp.retrofit.RetrofitInstance
import com.example.quizapp.retrofit.model.QuizCategory
import com.example.quizapp.retrofit.model.QuizQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object QuizRepository {

    private val api = RetrofitInstance.api

    private val _categories = MutableLiveData<List<QuizCategory>>()
    val categories: LiveData<List<QuizCategory>>
        get() = _categories

    private val _questions = MutableLiveData<List<QuizQuestion>>()
    val questions: LiveData<List<QuizQuestion>>
        get() = _questions


    suspend fun getCategories() = withContext(Dispatchers.IO) {
        val response = api.getCategories()
        if (response.isSuccessful) {
            _categories.postValue(response.body()?.categories)
        }
    }

    suspend fun getQuestions(categoryId: Int) = withContext(Dispatchers.IO) {
        val response = api.getQuestions(categoryId)
        if (response.isSuccessful) {
            _questions.postValue(response.body()?.results)
        }
    }
}