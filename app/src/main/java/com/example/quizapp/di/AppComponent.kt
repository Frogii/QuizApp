package com.example.quizapp.di

import com.example.quizapp.di.module.RepositoryModule
import com.example.quizapp.ui.history.HistoryFragment
import com.example.quizapp.ui.categories.CategoriesFragment
import com.example.quizapp.ui.questions.QuestionsFragment
import com.example.quizapp.ui.score.ScoreFragment
import dagger.Component

@AppScope
@Component(modules = [RepositoryModule::class])
interface AppComponent {

    fun inject(fragment: CategoriesFragment)
    fun inject(fragment: QuestionsFragment)
    fun inject(fragment: HistoryFragment)
    fun inject(fragment: ScoreFragment)
}