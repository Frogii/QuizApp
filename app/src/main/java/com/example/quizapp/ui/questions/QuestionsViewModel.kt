package com.example.quizapp.ui.questions

import android.os.CountDownTimer
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
import com.example.quizapp.util.TimerStatus
import kotlinx.coroutines.*

class QuestionsViewModel(private val quizRepository: QuizRepository, category: QuizCategory) :
    ViewModel() {

    private val _downloadStatus = MutableLiveData<ApiStatus>()
    val downloadStatus: LiveData<ApiStatus>
        get() = _downloadStatus

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


    val timerEvent = SingleLiveEvent<TimerStatus>()
    private var timerProgress: CountDownTimer? = null
    private var timerSeconds: CountDownTimer? = null
    private var timerJob: Job? = null

    private val _progress = MutableLiveData(0)
    val progress: LiveData<Int> get() = _progress

    private val _seconds = MutableLiveData(15)
    val seconds: LiveData<Int> get() = _seconds

    init {
        _category.value = category
        getQuestions()
    }

    private fun getQuestions() {
        viewModelScope.launch {
            _downloadStatus.value = ApiStatus.LOADING
            try {
                category.value?.id?.let { id ->
                    val response = quizRepository.getQuestions(id)
                    if (response.isSuccessful) {
                        _questions.value = response.body()?.results
                        if (response.body()?.results?.isEmpty() == true) {
                            _downloadStatus.value = ApiStatus.EMPTY
                        } else {
                            _downloadStatus.value = ApiStatus.DONE
                            setTimerEvent(TimerStatus.START)
                        }
                    }
                }
            } catch (e: Exception) {
                _downloadStatus.value = ApiStatus.ERROR
                Log.d("myLog", e.message.toString())
            }
        }
    }

    fun checkAnswer(answer: Boolean) = viewModelScope.launch {
        setTimerEvent(TimerStatus.STOP)
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
                setTimerEvent(TimerStatus.START)
            } else {
                moveToScoreFragment(rightAnswers.value!!)
            }
            Log.d("myLog", "position - ${position.value.toString()}")
        }
    }

    fun startTimer() {
        timerJob = viewModelScope.launch {
            _seconds.value = 15
            _progress.value = 0
            timerProgress = object : CountDownTimer(15500, 100) {

                override fun onTick(millisUntilFinished: Long) {
                    _progress.value = _progress.value!! + 1
                }

                override fun onFinish() {
                }
            }.start()
            delay(500)
            timerSeconds = object : CountDownTimer(15000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    _seconds.value = _seconds.value!! - 1
                }

                override fun onFinish() {
                    timerEvent.value = TimerStatus.TIMEOUT
                }
            }.start()
        }
    }

    fun stopTimer() {
        timerProgress?.cancel()
        timerSeconds?.cancel()
        timerJob?.cancel()
    }

    fun timeout() = viewModelScope.launch {
        stopTimer()
        questions.value?.let { questionsList ->
            if (position.value!! < questionsList.size - 1) {
                _position.value = position.value!!.plus(1)
                setTimerEvent(TimerStatus.START)
            } else {
                moveToScoreFragment(rightAnswers.value!!)
            }
        }
    }

    private fun showAnswerResult(checkedAnswer: Boolean) {
        answerResultEvent.value = checkedAnswer
    }

    private fun moveToScoreFragment(rightAnswers: Int) {
        scoreFragmentEvent.value = rightAnswers
    }

    private fun setTimerEvent(timerStatus: TimerStatus) {
        timerEvent.value = timerStatus
    }
}