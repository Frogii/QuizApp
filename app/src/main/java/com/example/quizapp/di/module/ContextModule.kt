package com.example.quizapp.di.module

import android.content.Context
import com.example.quizapp.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {

    @AppScope
    @Provides
    fun provideContext(): Context = this.context.applicationContext

}