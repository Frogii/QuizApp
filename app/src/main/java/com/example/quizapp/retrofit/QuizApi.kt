package com.example.quizapp.retrofit

import com.example.quizapp.retrofit.model.QuizCategories
import retrofit2.Response
import retrofit2.http.GET

interface QuizApi {

    @GET("/api_category.php")
    suspend fun getCategories() : Response<QuizCategories>
}