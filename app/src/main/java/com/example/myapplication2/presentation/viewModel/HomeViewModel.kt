package com.example.myapplication2.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.domain.model.ResultsModel
import com.example.myapplication2.domain.useCase.ResultsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val resultsUseCase: ResultsUseCase
): ViewModel() {
    var list: Flow<ResultsModel> = flowOf()
    fun getLastTable() {
        viewModelScope.launch {
            list = resultsUseCase.getLastTR()
        }
    }

}