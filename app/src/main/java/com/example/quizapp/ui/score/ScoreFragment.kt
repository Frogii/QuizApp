package com.example.quizapp.ui.score

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.App
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentScoreBinding
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.room.model.QuizTry
import com.example.quizapp.util.AppDateUtils
import javax.inject.Inject

class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding
    private lateinit var args: ScoreFragmentArgs
    private lateinit var scoreViewModel: ScoreViewModel

    @Inject
    lateinit var quizRepository: QuizRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = ScoreFragmentArgs.fromBundle(requireArguments())
        App.dagerAppComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)
        binding.score = args.rightAnswers
        scoreViewModel = ViewModelProvider(
            this,
            ScoreViewModelFactory(quizRepository)
        ).get(ScoreViewModel::class.java)
        scoreViewModel.addAttempt(QuizTry(args.category.shortName, args.rightAnswers, AppDateUtils.getCurrentDay()))
        binding.buttonTryAgain.setOnClickListener {
            findNavController().navigate(
                ScoreFragmentDirections.actionScoreFragmentToQuestionsFragment(
                    args.category
                )
            )
        }
        binding.buttonFinish.setOnClickListener {
            findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToCategoriesFragment())
        }
        return binding.root
    }
}