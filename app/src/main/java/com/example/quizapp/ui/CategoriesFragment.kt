package com.example.quizapp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.adapter.CategoriesRecAdapter
import com.example.quizapp.databinding.FragmentCategoriesBinding
import com.example.quizapp.repository.QuizRepository

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var categoriesRecAdapter: CategoriesRecAdapter
    private lateinit var categoriesViewModel: CategoriesViewModel
    private lateinit var categoriesViewModelFactory: CategoriesViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        categoriesViewModelFactory = CategoriesViewModelFactory(QuizRepository)
        categoriesViewModel = ViewModelProvider(this, categoriesViewModelFactory).get(CategoriesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = categoriesViewModel
        categoriesRecAdapter = CategoriesRecAdapter { category ->
            findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToQuestionsFragment(category))
        }
        binding.recyclerViewCategories.adapter = categoriesRecAdapter

        return binding.root
    }


}