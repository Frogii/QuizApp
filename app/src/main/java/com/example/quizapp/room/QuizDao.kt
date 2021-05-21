package com.example.quizapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.quizapp.room.model.QuizTry

@Dao
interface QuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttempt(quizTry: QuizTry)

    @Query("SELECT * FROM quiz_history")
    suspend fun getAllAttempts() : List<QuizTry>

    @Query("DELETE FROM quiz_history")
    suspend fun deleteAllAttempts()
}