package com.example.quizapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.HistoryItemBinding
import com.example.quizapp.room.model.QuizTry

class HistoryRecAdapter : RecyclerView.Adapter<HistoryRecAdapter.HistoryViewHolder>() {

    var historyList = listOf<QuizTry>()

    fun setList(list: List<QuizTry>) {
        this.historyList = list
        notifyDataSetChanged()
    }

    class HistoryViewHolder(val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(quizTry: QuizTry) {
            binding.quizTry = quizTry
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(historyList[position])
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}