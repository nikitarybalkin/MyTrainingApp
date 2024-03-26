package com.example.myapplication2.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.domain.model.ResultsModel
import com.example.myapplication2.domain.useCase.ResultsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ResultsViewModel @Inject constructor(private val resultsUseCase: ResultsUseCase) : ViewModel() {
    var listOfExers: Flow<List<ResultsModel>>? = MutableStateFlow(emptyList())
    fun getAll() {
        viewModelScope.launch {

            listOfExers = resultsUseCase.getAll()

        }
    }

}