package com.example.quizapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.HistoryItemBinding

class HistoryRecAdapter : RecyclerView.Adapter<HistoryRecAdapter.HistoryViewHolder>() {

    var historyList = listOf<Any>()

    fun setList(list: List<Any>) {
        this.historyList = list
    }

    class HistoryViewHolder(val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(HistoryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        TODO("Not yet implemented")
        TODO("CREATE PLACEHOLDERS in strings")
    }

    override fun getItemCount(): Int {
        return historyList.size
    }
}