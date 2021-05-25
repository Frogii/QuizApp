package com.example.quizapp.ui.history

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quizapp.App
import com.example.quizapp.R
import com.example.quizapp.adapter.HistoryRecAdapter
import com.example.quizapp.databinding.FragmentHistoryBinding
import com.example.quizapp.repository.QuizRepository
import javax.inject.Inject

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var historyRecAdapter: HistoryRecAdapter
    @Inject
    lateinit var quizRepository: QuizRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.daggerAppComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        setHasOptionsMenu(true)
        historyViewModel = ViewModelProvider(
            this,
            HistoryViewModelFactory(quizRepository)
        ).get(HistoryViewModel::class.java)
        binding.lifecycleOwner = this
        binding.historyViewModel = historyViewModel
        setupRecycler()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.history_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuClearHistory) {
            historyViewModel.clearHistory()
            val toast = Toast.makeText(
                context,
                "History cleared",
                Toast.LENGTH_SHORT
            )
            toast.setGravity(Gravity.CENTER_HORIZONTAL, 0 ,0)
            toast.show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecycler() {
        historyRecAdapter = HistoryRecAdapter()
        binding.recyclerViewHistory.adapter = historyRecAdapter
        binding.recyclerViewHistory.layoutManager = LinearLayoutManager(context)
    }
}