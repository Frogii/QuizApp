package com.example.quizapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding
    private lateinit var args: ScoreFragmentArgs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args = ScoreFragmentArgs.fromBundle(requireArguments())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)
        binding.score = args.rightAnswers
        binding.buttonTryAgain.setOnClickListener {
            findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToQuestionsFragment(args.category))
        }
        binding.buttonFinish.setOnClickListener {
            findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToCategoriesFragment())
        }
        return binding.root
    }
}