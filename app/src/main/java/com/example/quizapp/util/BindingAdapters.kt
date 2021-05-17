package com.example.quizapp.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.adapter.CategoriesRecAdapter
import com.example.quizapp.retrofit.model.QuizCategory
import com.example.quizapp.ui.ApiStatus

@BindingAdapter("dataList")
fun bindRecyclerViewData(recyclerView: RecyclerView, data: List<QuizCategory>?) {
    val adapter = recyclerView.adapter as CategoriesRecAdapter
    data?.let { list ->
        adapter.submitList(list)
    }
}

@BindingAdapter("apiStatus")
fun bindApiStatus(imageView: ImageView, status: ApiStatus) {
    when (status) {
        ApiStatus.LOADING -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            imageView.visibility = View.GONE
        }
    }
}
