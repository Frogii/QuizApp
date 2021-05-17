package com.example.quizapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.CategoryItemBinding
import com.example.quizapp.retrofit.model.QuizCategory

class CategoriesRecAdapter(val onAdapterItemClick: (QuizCategory) -> Unit) :
    ListAdapter<QuizCategory, CategoriesRecAdapter.CategoriesViewHolder>(DiffCallback) {

    inner class CategoriesViewHolder(val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: QuizCategory) {
            binding.category = category
            binding.root.setOnClickListener {
                onAdapterItemClick(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        return CategoriesViewHolder(CategoryItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback: DiffUtil.ItemCallback<QuizCategory>() {
        override fun areItemsTheSame(oldItem: QuizCategory, newItem: QuizCategory): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: QuizCategory, newItem: QuizCategory): Boolean {
            return oldItem.id == newItem.id
        }
    }
}