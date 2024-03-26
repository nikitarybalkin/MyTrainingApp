package com.example.myapplication2.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.domain.model.ResultsModel
import com.example.myapplication2.domain.model.TrainingModel
import com.example.myapplication2.domain.useCase.ResultsUseCase
import com.example.myapplication2.domain.useCase.TrainingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule

class TrainViewModel @Inject constructor(private val resultsUseCase: ResultsUseCase, private val trainingUseCase: TrainingUseCase) : ViewModel() {
    var resList : Flow<List<TrainingModel>> = MutableStateFlow(emptyList())
    var exesList : Flow<String> = flowOf()
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

    fun insert(table: ResultsModel) {
        viewModelScope.launch { resultsUseCase.insert(table) }

    }

    fun startTimer() {
        timer.schedule(delay = 0, period = 1000) {
            time++
        }

    }
    fun stopTimer() {
        timer.cancel()
        }

}