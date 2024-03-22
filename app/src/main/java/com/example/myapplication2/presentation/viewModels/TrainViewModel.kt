package com.example.myapplication2.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.ExerciseDao
import com.example.myapplication2.data.ResultsDao
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.data.TrainingEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule

class TrainViewModel @Inject constructor(var resultsDao: ResultsDao, var trainingDao: ExerciseDao) : ViewModel() {
    var resList : Flow<List<TrainingEntity>> = MutableStateFlow(emptyList())
    var exesList : Flow<List<String>> = MutableStateFlow(emptyList())
    var time: Int = 0
    var timer = Timer()
    fun getAll() {

        viewModelScope.launch {
            resList = trainingDao.getAll()
        }

    }
    fun getListOfExercises(nameOfTr: String) {
        viewModelScope.launch {
            exesList = trainingDao.getOneTrainExercises(nameOfTr)
        }
    }

    fun insert(table: ResultsEntity) {
        viewModelScope.launch { resultsDao.insert(table) }

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