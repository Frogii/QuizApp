package com.example.quizapp.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.adapter.CategoriesRecAdapter
import com.example.quizapp.retrofit.model.QuizCategory

@BindingAdapter("dataList")
fun bindRecyclerViewData(recyclerView: RecyclerView, data: List<QuizCategory>?) {
    val adapter = recyclerView.adapter as CategoriesRecAdapter
    data?.let { list ->
        adapter.setList(list)
    }
}
