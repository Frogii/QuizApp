package com.example.quizapp.util

import com.example.quizapp.model.QuizCategory

object Constants {

    const val BASE_URL = "https://opentdb.com"

    val sampleCategoryList = listOf<QuizCategory>(
        QuizCategory(1, "Animals"),
        QuizCategory(2, "Cars"),
        QuizCategory(3, "Trees"),
        QuizCategory(4, "Countries"),
        QuizCategory(5, "Politics"),
        QuizCategory(6, "Birds"),
        QuizCategory(7, "Games"),
        QuizCategory(8, "Movies"),
        QuizCategory(9, "Pictures"),
        QuizCategory(10, "Cartoons"),
        QuizCategory(11, "Hardware"),
        QuizCategory(12, "Flowers"),
        QuizCategory(13, "Phones"),
        QuizCategory(14, "Math"),
        QuizCategory(15, "Astronomy"),
        QuizCategory(16, "Food"),
        QuizCategory(17, "Sports")
    )
}