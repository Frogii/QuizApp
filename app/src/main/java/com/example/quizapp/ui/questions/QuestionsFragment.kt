package com.example.quizapp.ui.questions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.App
import com.example.quizapp.R
import com.example.quizapp.adapter.QuestionsRecAdapter
import com.example.quizapp.databinding.FragmentQuestionsBinding
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.ui.QuizActivity
import javax.inject.Inject

class QuestionsFragment : Fragment() {

    private lateinit var binding: FragmentQuestionsBinding
    private lateinit var args: QuestionsFragmentArgs
    private lateinit var questionsRecAdapter: QuestionsRecAdapter
    private lateinit var questionsViewModelFactory: QuestionsViewModelFactory
    private lateinit var viewModel: QuestionsViewModel
    @Inject
    lateinit var quizRepository: QuizRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.dagerAppComponent.inject(this)
        args = QuestionsFragmentArgs.fromBundle(requireArguments())
        (activity as QuizActivity).supportActionBar?.title = args.category.shortName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_questions, container, false)
        questionsViewModelFactory = QuestionsViewModelFactory(quizRepository, args.category)
        viewModel =
            ViewModelProvider(this, questionsViewModelFactory).get(QuestionsViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        questionsRecAdapter = QuestionsRecAdapter { answer ->
            viewModel.checkAnswer(answer)
        }
        binding.viewPagerQuestions.adapter = questionsRecAdapter
        Log.d("MYLOG", quizRepository.toString())
        Log.d("MYLOG", quizRepository.api.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPagerQuestions.apply {
            isUserInputEnabled = false
            viewModel.position.observe(viewLifecycleOwner, Observer { position ->
                this.currentItem = position
            })
        }

        viewModel.answerResultEvent.observe(viewLifecycleOwner, Observer { answer ->
            binding.answer = answer
            binding.motionLayoutQuestions.transitionToEnd()
            binding.motionLayoutQuestions.visibility = View.GONE
            binding.motionLayoutQuestions.transitionToStart()
            binding.motionLayoutQuestions.visibility = View.VISIBLE
        })

        viewModel.scoreFragmentEvent.observe(viewLifecycleOwner, Observer { rightAnswers ->
            findNavController().navigate(
                QuestionsFragmentDirections.actionQuestionsFragmentToScoreFragment(
                    rightAnswers,
                    args.category
                )
            )
        })
    }
}