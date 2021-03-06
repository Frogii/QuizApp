package com.example.quizapp.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.quizapp.R
import com.example.quizapp.adapter.CategoriesRecAdapter
import com.example.quizapp.adapter.HistoryRecAdapter
import com.example.quizapp.adapter.QuestionsRecAdapter
import com.example.quizapp.retrofit.model.QuizCategory
import com.example.quizapp.retrofit.model.QuizQuestion
import com.example.quizapp.room.model.QuizTry
import com.google.android.material.snackbar.Snackbar

@BindingAdapter("dataList")
fun bindRecyclerViewData(recyclerView: RecyclerView, data: List<QuizCategory>?) {
    val adapter = recyclerView.adapter as CategoriesRecAdapter
    data?.let { list ->
        adapter.submitList(list)
    }
}

@BindingAdapter("questionsList")
fun bindQuestionRecycler(viewPager2: ViewPager2, data: List<QuizQuestion>?) {
    val adapter = viewPager2.adapter as QuestionsRecAdapter
    data?.let { list ->
        adapter.setList(list)
    }
}

@BindingAdapter("attemptsList")
fun bindAttemptsRecycler(recyclerView: RecyclerView, data: List<QuizTry>?) {
    val adapter = recyclerView.adapter as HistoryRecAdapter
    data?.let { list ->
        adapter.setList(list)
    }
}

@BindingAdapter("apiStatus")
fun bindApiStatus(imageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
            Snackbar.make(imageView, "No connection", Snackbar.LENGTH_SHORT).show()
        }
        ApiStatus.DONE -> {
            imageView.visibility = View.GONE
        }
        ApiStatus.EMPTY -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_sad_face)
            Snackbar.make(imageView, "No questions for this category", Snackbar.LENGTH_SHORT).show()
        }
    }
}

@BindingAdapter("roomStatus")
fun bindRoomStatus(textView: TextView, status: RoomStatus?) {
    when (status) {
        RoomStatus.DONE -> {
            textView.visibility = View.GONE
        }
        RoomStatus.EMPTY -> {
            textView.visibility = View.VISIBLE
        }
    }
}
