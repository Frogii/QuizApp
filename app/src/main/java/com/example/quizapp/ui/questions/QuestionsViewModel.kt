package com.example.quizapp.ui.questions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.retrofit.model.QuizCategory
import com.example.quizapp.retrofit.model.QuizQuestion
import com.example.quizapp.util.ApiStatus
import com.example.quizapp.util.SingleLiveEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuestionsViewModel(private val quizRepository: QuizRepository, category: QuizCategory) :
    ViewModel() {

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val _category = MutableLiveData<QuizCategory>()
    val category: LiveData<QuizCategory>
        get() = _category

    private val _questions = MutableLiveData<List<QuizQuestion>>()
    val questions: LiveData<List<QuizQuestion>>
        get() = _questions

    private val _rightAnswers = MutableLiveData(0)
    val rightAnswers: LiveData<Int>
        get() = _rightAnswers

    private val _position = MutableLiveData(0)
    val position: LiveData<Int>
        get() = _position

    val answerResultEvent = SingleLiveEvent<Boolean>()

    val scoreFragmentEvent = SingleLiveEvent<Int>()

    val difficulty = quizRepository.difficulty.level

    init {
        _category.value = category
        getQuestions()
    }

    private fun getQuestions() {
        viewModelScope.launch {
            _status.value = ApiStatus.LOADING
            try {
                category.value?.id?.let { id ->
                    val response = quizRepository.getQuestions(id)
                    if (response.isSuccessful) {
                        _questions.value = response.body()?.results
                        if (response.body()?.results?.isEmpty() == true) {
                            _status.value = ApiStatus.EMPTY
                        } else {
                            _status.value = ApiStatus.DONE
                        }
                    }
                }
            } catch (e: Exception) {
                _status.value = ApiStatus.ERROR
                Log.d("myLog", e.message.toString())
            }
        }
    }

    fun checkAnswer(answer: Boolean) = viewModelScope.launch {
        questions.value?.let { questionsList ->
            if (answer == questionsList[position.value!!].correctAnswer) {
                _rightAnswers.value = rightAnswers.value!!.plus(1)
                showAnswerResult(true)
                Log.d("myLog", "rightAnswers - ${rightAnswers.value.toString()}")
            } else {
                showAnswerResult(false)
            }
            delay(1000)
            if (position.value!! < questionsList.size - 1) {
                _position.value = position.value!!.plus(1)
            } else {
                moveToScoreFragment(rightAnswers.value!!)
            }
            Log.d("myLog", "position - ${position.value.toString()}")
        }
    }

    private fun showAnswerResult(checkedAnswer: Boolean) {
        answerResultEvent.value = checkedAnswer
    }

    private fun moveToScoreFragment(rightAnswers: Int) {
        scoreFragmentEvent.value = rightAnswers
    }
}