package com.example.myapplication2.presentation.viewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.TrainingDao
import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.domain.ResultsUseCase
import com.example.myapplication2.domain.TrainingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyTrainingsViewModel @Inject constructor(
    private val trainingUseCase: TrainingUseCase
) : ViewModel() {
    var _list: Flow<List<TrainingEntity>>? = MutableStateFlow(emptyList())

    fun getAll() {
        Log.d("MyTrainings", "nameOfVM=$this")
        viewModelScope.launch {

            _list = trainingUseCase.getAll()
            Log.d(TAG, "получаемая таблица = $_list.")

        }

    }

    fun delete(table: TrainingEntity) {
        viewModelScope.launch {

            trainingUseCase.delete(table)

        }
    }


}