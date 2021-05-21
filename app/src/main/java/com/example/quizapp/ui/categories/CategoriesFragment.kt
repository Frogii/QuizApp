package com.example.quizapp.ui.categories

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.App
import com.example.quizapp.R
import com.example.quizapp.adapter.CategoriesRecAdapter
import com.example.quizapp.databinding.FragmentCategoriesBinding
import com.example.quizapp.repository.QuizRepository
import javax.inject.Inject
import com.example.quizapp.retrofit.model.QuizCategory
import com.example.quizapp.util.QuizDifficulty

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var categoriesRecAdapter: CategoriesRecAdapter
    private lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var categoriesViewModelFactory: CategoriesViewModelFactory

    @Inject
    lateinit var quizRepository: QuizRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.dagerAppComponent.inject(this)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        binding.lifecycleOwner = this
        categoriesViewModelFactory = CategoriesViewModelFactory(quizRepository)
        categoriesViewModel =
            ViewModelProvider(this, categoriesViewModelFactory).get(CategoriesViewModel::class.java)
        binding.viewModel = categoriesViewModel

        categoriesRecAdapter = CategoriesRecAdapter { category ->
            categoriesViewModel.setQuestionsEvent(category)
        }
        binding.recyclerViewCategories.adapter = categoriesRecAdapter
        categoriesViewModel.moveToQuestionsFragment.observe(this, Observer { category ->
            moveToQuestions(category)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.difficulty_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        categoriesViewModel.changeDifficulty(
            when (item.itemId) {
                R.id.difficulty_medium -> QuizDifficulty.MEDIUM
                R.id.difficulty_hard -> QuizDifficulty.HARD
                else -> QuizDifficulty.EASY
            }
        )
        return super.onOptionsItemSelected(item)
    }

    private fun moveToQuestions(category: QuizCategory) {
        findNavController().navigate(
            CategoriesFragmentDirections.actionCategoriesFragmentToQuestionsFragment(category)
        )
    }
}