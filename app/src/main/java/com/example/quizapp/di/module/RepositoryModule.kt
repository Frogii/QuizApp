package com.example.quizapp.di.module

import com.example.quizapp.di.AppScope
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.retrofit.QuizApi
import com.example.quizapp.room.QuizDatabase
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, RoomModule::class])
class RepositoryModule {

    @AppScope
    @Provides
    fun provideQuizRepository(api: QuizApi, db: QuizDatabase): QuizRepository {
        return QuizRepository(api, db)
    }
}