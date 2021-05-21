package com.example.quizapp

import android.app.Application
import com.example.quizapp.di.DaggerAppComponent
import com.example.quizapp.di.module.ContextModule

class App : Application() {

    companion object {
        lateinit var dagerAppComponent: DaggerAppComponent
    }

    override fun onCreate() {
        super.onCreate()
        dagerAppComponent =
            DaggerAppComponent.builder()
                .contextModule(ContextModule(applicationContext))
                .build() as DaggerAppComponent
    }
}