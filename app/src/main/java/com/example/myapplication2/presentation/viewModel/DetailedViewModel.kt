package com.example.myapplication2.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.domain.model.TrainingModel
import com.example.myapplication2.domain.useCase.TrainingUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailedViewModel @Inject constructor(private val trainingUseCase: TrainingUseCase) :
    ViewModel() {
    var listOfExers: Flow<List<TrainingModel>> = flowOf()

    fun getList() {

        viewModelScope.launch {

            listOfExers = trainingUseCase.getAll()

        }
    }
}