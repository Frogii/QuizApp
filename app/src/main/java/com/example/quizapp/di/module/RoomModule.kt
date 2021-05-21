package com.example.quizapp.di.module

import android.content.Context
import com.example.quizapp.di.AppScope
import com.example.quizapp.room.QuizDatabase
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class RoomModule {

    @AppScope
    @Provides
    fun provideQuizDatabase(context: Context) = QuizDatabase.createDatabase(context)
}