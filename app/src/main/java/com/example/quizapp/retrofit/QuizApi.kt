package com.example.quizapp.retrofit

import com.example.quizapp.retrofit.model.QuizCategoriesResult
import com.example.quizapp.retrofit.model.QuizQuestionsResult
import com.example.quizapp.util.QuizDifficulty
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {

    @GET("/api_category.php")
    suspend fun getCategories(): Response<QuizCategoriesResult>

    @GET("/api.php?amount=10&type=boolean")
    suspend fun getQuestions(
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String
    ): Response<QuizQuestionsResult>
}