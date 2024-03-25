package com.example.myapplication2.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.TrainingDao
import com.example.myapplication2.data.ResultsDao
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.domain.ResultsUseCase
import com.example.myapplication2.domain.TrainingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule

class TrainViewModel @Inject constructor(private val resultsUseCase: ResultsUseCase, private val trainingUseCase: TrainingUseCase) : ViewModel() {
    var resList : Flow<List<TrainingEntity>> = MutableStateFlow(emptyList())
    var exesList : Flow<List<String>> = MutableStateFlow(emptyList())
    var time: Int = 0
    var timer = Timer()
    fun getAll() {

        viewModelScope.launch {
            resList = trainingUseCase.getAll()
        }

    }
    fun getListOfExercises(nameOfTr: String) {
        viewModelScope.launch {
            exesList = trainingUseCase.getOneTrainExercises(nameOfTr)
        }
    }

    fun insert(table: ResultsEntity) {
        viewModelScope.launch { resultsUseCase.insert(table) }

    }

    fun startTimer() {
        timer.schedule(delay = 0, period = 1000) {
            time++
        }

        /*viewModelScope.launch{
            delay(1000)
            time++
        }

         */

    }
    fun stopTimer() {
        timer.cancel()
        }

}