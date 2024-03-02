package com.example.myapplication2.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.db.AppDataBase
import com.example.myapplication2.db.ExerciseDao
import com.example.myapplication2.db.TrainingEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyTrainingsViewModel(private val trainingDao: ExerciseDao) : ViewModel() {
    var _list: Flow<List<TrainingEntity>>? = MutableStateFlow(emptyList())
    //val list = _list

    fun getAll() {

        viewModelScope.launch {

            _list = trainingDao.getAll()
            Log.d(TAG, "получаемая таблица = $_list.")

        }

    }

    fun delete(table: TrainingEntity) {
        viewModelScope.launch {

            trainingDao.delete(table)

        }
    }


}