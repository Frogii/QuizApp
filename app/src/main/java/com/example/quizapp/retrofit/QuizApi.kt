package com.example.quizapp.retrofit

import com.example.quizapp.retrofit.model.QuizCategories
import com.example.quizapp.retrofit.model.QuizQuestions
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {

    @GET("/api_category.php")
    suspend fun getCategories(): Response<QuizCategories>

    @GET("/api.php?amount=10&difficulty=easy&type=boolean")
    suspend fun getQuestions(
        @Query("category") category: Int
    ): Response<QuizQuestions>
}