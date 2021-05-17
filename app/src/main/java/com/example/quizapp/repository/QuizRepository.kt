package com.example.quizapp.repository

import com.example.quizapp.retrofit.RetrofitInstance
import com.example.quizapp.retrofit.model.QuizCategories
import retrofit2.Response

object QuizRepository {

    private val api = RetrofitInstance.api

    suspend fun getCategories(): Response<QuizCategories> = api.getCategories()
}