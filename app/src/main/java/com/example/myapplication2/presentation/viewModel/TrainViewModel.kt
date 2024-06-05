package com.example.myapplication2.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.domain.model.ResultsModel
import com.example.myapplication2.domain.model.TrainingModel
import com.example.myapplication2.domain.useCase.ResultsUseCase
import com.example.myapplication2.domain.useCase.TrainingUseCase
import com.example.myapplication2.presentation.fragment.TrainBottomSheetFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject
import kotlin.concurrent.schedule

class TrainViewModel @Inject constructor(private val resultsUseCase: ResultsUseCase, private val trainingUseCase: TrainingUseCase) : ViewModel() {
    var resList : Flow<List<TrainingModel>> = MutableStateFlow(emptyList())
    var resListValue : List<TrainingModel> = listOf()
    var exesList : Flow<String> = flowOf()
    var indexOfExercise: MutableLiveData<Int?> = MutableLiveData(null)
    var time: Int = 0
    var timer: Timer? = null
    var timerTask: TimerTask? = null
    var counter: MutableLiveData<Int> = MutableLiveData(0)
    var bottomSheetFragment: TrainBottomSheetFragment? = null
    var numbOfList: Int? = null

    fun getAll() {
        Log.d("LOL", "viewModel = $viewModelScope")
        viewModelScope.launch {
            resList = trainingUseCase.getAll()
            resList.collect {
                resListValue = it
            }
        }
    }

    fun getListOfExercises(nameOfTr: String) {
        viewModelScope.launch {
            exesList = trainingUseCase.getOneTrainExercises(nameOfTr)
        }
    }

    fun insert(table: ResultsModel) {
        viewModelScope.launch { resultsUseCase.insert(table) }
        Log.d("LOL","insert is vm. Table = ${table.weights}")

    }

    fun startTimer() {
        timer = Timer()
        timerTask = object : TimerTask() {
            override fun run() {
                time++
            }

        }
        timer?.schedule(timerTask, 0, 1000)
    }
    fun stopTimer() {
        timerTask?.cancel()
        timer?.cancel()
        timer = null
        timerTask = null
        }

}