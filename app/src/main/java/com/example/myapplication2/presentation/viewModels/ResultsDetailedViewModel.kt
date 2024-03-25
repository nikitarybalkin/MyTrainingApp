package com.example.myapplication2.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.ResultsDao
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.domain.ResultsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ResultsDetailedViewModel @Inject constructor(private val resultsUseCase: ResultsUseCase) : ViewModel() {
    var resList: Flow<List<ResultsEntity>> = MutableStateFlow(emptyList())
    fun getAll() {
        viewModelScope.launch {
            resList = resultsUseCase.getAll()
        }
    }

}