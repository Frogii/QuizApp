package com.example.quizapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.room.model.QuizTry
import com.example.quizapp.util.RoomStatus
import kotlinx.coroutines.launch

class HistoryViewModel(val quizRepository: QuizRepository) : ViewModel() {

    private val _history = MutableLiveData<List<QuizTry>>()
    val history: LiveData<List<QuizTry>>
        get() = _history

    private val _status = MutableLiveData<RoomStatus>()
    val status: LiveData<RoomStatus>
        get() = _status

    init {
        getAllAttempts()
    }

    private fun getAllAttempts() {
        viewModelScope.launch {
            getAttempts()
        }
    }

    private fun deleteAllAttempts() {
        viewModelScope.launch {
            quizRepository.deleteAllAttempts()
            getAttempts()
        }
    }

    fun clearHistory() {
        deleteAllAttempts()
    }

    private suspend fun getAttempts() {
        _history.value = quizRepository.getAllAttempts()
        if (_history.value.isNullOrEmpty()) {
            _status.value = RoomStatus.EMPTY
        } else {
            _status.value = RoomStatus.DONE
        }
    }
}