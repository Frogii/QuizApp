package com.example.quizapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.QuestionItemBinding
import com.example.quizapp.model.QuizQuestion

class QuestionsRecAdapter : RecyclerView.Adapter<QuestionsRecAdapter.QuestionViewHolder>() {

    private var questionsList = listOf<QuizQuestion>()

    fun setList(data: List<QuizQuestion>) {
        this.questionsList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(QuestionItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return questionsList.size
    }

    class QuestionViewHolder(val binding: QuestionItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}