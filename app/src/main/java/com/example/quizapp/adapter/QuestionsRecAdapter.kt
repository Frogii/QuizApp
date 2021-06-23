package com.example.quizapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.QuestionItemBinding
import com.example.quizapp.retrofit.model.QuizQuestion

class QuestionsRecAdapter(val answerClick: (Boolean) -> Unit) :
    RecyclerView.Adapter<QuestionsRecAdapter.QuestionViewHolder>() {

    private var questionsList = listOf<QuizQuestion>()

    fun setList(data: List<QuizQuestion>) {
        this.questionsList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            QuestionItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(questionsList[position])
        holder.binding.motionLayoutQuestionItem.transitionToStart()
    }

    override fun getItemCount(): Int {
        return questionsList.size
    }

    inner class QuestionViewHolder(val binding: QuestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(quizQuestion: QuizQuestion) {
            binding.question = quizQuestion
            binding.buttonTrue.setOnClickListener {
                answerClick(true)
                binding.motionLayoutQuestionItem.transitionToEnd()
            }
            binding.buttonFalse.setOnClickListener {
                answerClick(false)
                binding.motionLayoutQuestionItem.transitionToEnd()
            }
        }
    }
}