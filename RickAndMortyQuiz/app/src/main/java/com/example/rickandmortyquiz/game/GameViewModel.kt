package com.example.rickandmortyquiz.game

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.rickandmortyquiz.R

data class Question(
    val questionId: Int,
    val answer: Boolean,
    var attempted: Boolean = false, // if the user attempted to answer or not
    var answered: Boolean = false // which answer the user selected
)

class GameViewModel : ViewModel() {

    private var questionIndex = 0

    private lateinit var questionBank: MutableList<Question>

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _scoreString = MutableLiveData<String>()
    val scoreString: LiveData<String>
        get() = _scoreString

    private val _question = MutableLiveData<Int>()
    val question: LiveData<Int>
        get() = _question

    private val _attempted = MutableLiveData<Boolean>()
    val attempted: LiveData<Boolean>
        get() = _attempted

    private val _checkTrue = MutableLiveData<Boolean>()
    val checkTrue: LiveData<Boolean>
        get() = _checkTrue

    private val _checkFalse = MutableLiveData<Boolean>()
    val checkFalse: LiveData<Boolean>
        get() = _checkFalse

    private val _isCorrect = MutableLiveData<Boolean>()
    val isCorrect: LiveData<Boolean>
        get() = _isCorrect

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    init {
        _score.value = 0
        resetQuestionBank()
        newGame()
    }

    fun newGame() {
        resetQuestionBank()
        questionIndex = 0
        _eventGameFinish.value = false
        _question.value = questionBank[questionIndex].questionId
        updateQuestion()
    }

    private fun updateQuestion() {
        _question.value = questionBank[questionIndex].questionId
        _attempted.value = questionBank[questionIndex].attempted
        _isCorrect.value =
            questionBank[questionIndex].answer == questionBank[questionIndex].answered

        _checkFalse.value = !questionBank[questionIndex].answered
        _checkTrue.value = questionBank[questionIndex].answered

        _scoreString.value = "Your score is: ${_score.value} / ${questionBank.count()}"

        if (questionsAttempted() == questionBank.size) {
            onGameFinish()
        }
    }

    fun onAnswered(answerValue: Boolean) {
        questionBank[questionIndex].attempted = true

        _attempted.value = questionBank[questionIndex].attempted

        questionBank[questionIndex].answered = answerValue

        if (questionBank[questionIndex].answered == questionBank[questionIndex].answer) {
            _score.value = (_score.value)?.plus(1)
            _isCorrect.value = true
        } else {
            _isCorrect.value = false
        }

        updateQuestion()
    }

    fun nextQuestion() {
        _attempted.value = false

        if ((questionIndex + 1) == questionBank.size) {
            questionIndex = 0
        } else {
            _question.value = questionBank[questionIndex].questionId
            questionIndex++
        }

        updateQuestion()
    }

    fun prevQuestion() {
        if (questionIndex == 0) {
            questionIndex = questionBank.size - 1
        } else {
            questionIndex--
            _question.value = questionBank[questionIndex].questionId
        }

        updateQuestion()
    }

    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    fun questionsAttempted() = questionBank.count { it.attempted }

    private fun resetQuestionBank() {
        questionBank = mutableListOf(
            Question(R.string.question_1, false),
            Question(R.string.question_2, true),
            Question(R.string.question_3, true),
            Question(R.string.question_4, false),
            Question(R.string.question_5, false),
            Question(R.string.question_6, true),
            Question(R.string.question_7, false),
            Question(R.string.question_8, true),
            Question(R.string.question_9, false),
            Question(R.string.question_10, false),
            Question(R.string.question_11, false),
            Question(R.string.question_12, true),
            Question(R.string.question_13, false),
            Question(R.string.question_14, true),
            Question(R.string.question_15, false),
            Question(R.string.question_16, false),
            Question(R.string.question_17, true),
            Question(R.string.question_18, false),
            Question(R.string.question_19, false),
            Question(R.string.question_20, true)
        )
        questionBank.shuffle()
    }
}
