package com.example.myapplication2.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.domain.model.TrainingModel
import com.example.myapplication2.domain.useCase.TrainingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

class MyTrainingsViewModel @Inject constructor(
    private val trainingUseCase: TrainingUseCase
) : ViewModel() {
    var _list: Flow<List<TrainingModel>>? = flowOf()

    fun getAll() {
        viewModelScope.launch {
            _list = trainingUseCase.getAll()
        }

    }

    fun delete(table: TrainingModel) {
        viewModelScope.launch {

            trainingUseCase.delete(table)

        }
    }


}