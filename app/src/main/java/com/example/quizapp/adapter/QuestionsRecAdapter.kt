package com.example.quizapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.QuestionItemBinding
import com.example.quizapp.retrofit.model.QuizQuestion
import com.example.quizapp.retrofit.model.QuizQuestions

class QuestionsRecAdapter : RecyclerView.Adapter<QuestionsRecAdapter.QuestionViewHolder>() {

    private var questionsList = listOf<QuizQuestion>()

    fun setList(data: List<QuizQuestion>) {
        this.questionsList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(QuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questionsList[position])
    }

    override fun getItemCount(): Int {
        return questionsList.size
    }

    class QuestionViewHolder(val binding: QuestionItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(quizQuestion: QuizQuestion) {
            binding.question = quizQuestion
        }
    }
}