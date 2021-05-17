package com.example.quizapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.databinding.CategoryItemBinding
import com.example.quizapp.retrofit.model.QuizCategory

import com.example.quizapp.util.Constants

class CategoriesRecAdapter(val onAdapterItemClick: (QuizCategory) -> Unit) :
    RecyclerView.Adapter<CategoriesRecAdapter.CategoriesViewHolder>() {

    var categoriesList = listOf<QuizCategory>()

    fun setList(list: List<QuizCategory>) {
        this.categoriesList = list
        notifyDataSetChanged()
    }

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
        holder.bind(categoriesList[position])
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }
}