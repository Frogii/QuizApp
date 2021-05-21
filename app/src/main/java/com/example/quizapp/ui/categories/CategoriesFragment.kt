package com.example.quizapp.ui.categories

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
import com.example.quizapp.adapter.CategoriesRecAdapter
import com.example.quizapp.databinding.FragmentCategoriesBinding
import com.example.quizapp.repository.QuizRepository
import javax.inject.Inject

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
            findNavController().navigate(
                CategoriesFragmentDirections.actionCategoriesFragmentToQuestionsFragment(category)
            )
        }
        binding.recyclerViewCategories.adapter = categoriesRecAdapter
        return binding.root
    }
}