package com.example.myapplication2.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.ExerciseDao
import com.example.myapplication2.data.TrainingEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailedViewModel @Inject constructor(private val table: ExerciseDao) : ViewModel() {
    var listOfExers: Flow<List<TrainingEntity>> = MutableStateFlow(emptyList())

    fun getList() {

        viewModelScope.launch {

            listOfExers = table.getAll()

        }

    }

}