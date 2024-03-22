package com.example.myapplication2.presentation.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.ExerciseDao
import com.example.myapplication2.data.TrainingEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateTrainViewModel @Inject constructor(
    private val trainingsDao: ExerciseDao
) : ViewModel() {

    fun insert(train: TrainingEntity) {

        viewModelScope.launch {

            trainingsDao.insertTable(train)
            Log.d(TAG, "readytable = $train")

        }

    }

}