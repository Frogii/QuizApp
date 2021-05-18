package com.example.quizapp.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.adapter.QuestionsRecAdapter
import com.example.quizapp.databinding.FragmentQuestionsBinding
import com.example.quizapp.repository.QuizRepository

class QuestionsFragment : Fragment() {

    private lateinit var binding: FragmentQuestionsBinding
    private lateinit var args: QuestionsFragmentArgs
    private lateinit var questionsRecAdapter: QuestionsRecAdapter
    private lateinit var questionsViewModelFactory: QuestionsViewModelFactory
    private lateinit var viewModel: QuestionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_questions, container, false)
        args = QuestionsFragmentArgs.fromBundle(requireArguments())
        questionsViewModelFactory = QuestionsViewModelFactory(QuizRepository, args.category)
        viewModel = ViewModelProvider(this, questionsViewModelFactory).get(QuestionsViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        questionsRecAdapter = QuestionsRecAdapter()
        binding.viewPagerQuestions.adapter = questionsRecAdapter

        Toast.makeText(context, args.toString(), Toast.LENGTH_SHORT).show()
        return binding.root
    }
}