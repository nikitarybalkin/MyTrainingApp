package com.example.myapplication2.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.db.ExerciseDao
import com.example.myapplication2.db.ResultsDao
import com.example.myapplication2.db.ResultsEntity
import com.example.myapplication2.db.TrainingEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class TrainViewModel(var resultsDao: ResultsDao, var trainingDao: ExerciseDao) : ViewModel() {
    var resList : Flow<List<TrainingEntity>> = MutableStateFlow(emptyList())
    var exesList : Flow<List<String>> = MutableStateFlow(emptyList())
    var time: Int = 0
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

    fun timer() {

        viewModelScope.launch{
            delay(1000)
            time++
        }

    }

}