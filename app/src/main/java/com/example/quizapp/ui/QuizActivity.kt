package com.example.quizapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityQuizBinding
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_quiz)
        setContentView(binding.root)

        navController = Navigation.findNavController(this, R.id.navHostFragmentContainer)
        bottomNavView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.questionsFragment || destination.id == R.id.scoreFragment) {
                binding.bottomNavView.visibility = View.GONE
            } else binding.bottomNavView.visibility = View.VISIBLE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}