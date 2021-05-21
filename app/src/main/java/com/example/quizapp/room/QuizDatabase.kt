package com.example.quizapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizapp.room.model.QuizTry

@Database(
    entities = [QuizTry::class],
    version = 1
)
abstract class QuizDatabase : RoomDatabase() {

    abstract fun getQuizTryDao(): QuizDao

    companion object {
        fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                QuizDatabase::class.java,
                "QuizAttempts.db"
            )
                .build()
    }
}