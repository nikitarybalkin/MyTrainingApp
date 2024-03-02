package com.example.myapplication2.viewModels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.db.ExerciseDao
import com.example.myapplication2.db.TrainingEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailedViewModel(private val table: ExerciseDao) : ViewModel() {
    var listOfExers: Flow<List<TrainingEntity>> = MutableStateFlow(emptyList())

    fun getList() {

        viewModelScope.launch {

            listOfExers = table.getAll()

        }

    }

}