package com.example.quizapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quizapp.retrofit.QuizApi
import com.example.quizapp.retrofit.model.QuizCategory
import com.example.quizapp.room.QuizDatabase
import com.example.quizapp.room.model.QuizTry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuizRepository(val api: QuizApi, val db: QuizDatabase) {

    //Retrofit actions
    private val _categories = MutableLiveData<List<QuizCategory>>()
    val categories: LiveData<List<QuizCategory>>
        get() = _categories

    suspend fun getCategories() = withContext(Dispatchers.IO) {
        val response = api.getCategories()
        if (response.isSuccessful) {
            _categories.postValue(response.body()?.categories)
        }
    }

    suspend fun getQuestions(categoryId: Int) = api.getQuestions(categoryId)

    //Database actions
    suspend fun insertAttempt(quizTry: QuizTry) = db.getQuizTryDao().insertAttempt(quizTry)

    suspend fun getAllAttempts() = db.getQuizTryDao().getAllAttempts()

    suspend fun deleteAllAttempts() = db.getQuizTryDao().deleteAllAttempts()

}