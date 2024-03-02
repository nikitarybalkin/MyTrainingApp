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
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateTrainViewModel(private val trainingsDao : ExerciseDao) : ViewModel() {

    fun insert(train: TrainingEntity) {

        viewModelScope.launch {

            trainingsDao.insertTable(train)
            Log.d(TAG, "readytable = $train")

        }

    }



}