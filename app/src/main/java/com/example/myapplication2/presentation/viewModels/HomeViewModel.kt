package com.example.myapplication2.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.ResultsDao
import com.example.myapplication2.data.ResultsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val db: ResultsDao
): ViewModel() {
    var list: Flow<List<ResultsEntity>> = MutableStateFlow(emptyList())
    fun getLastTable() {
        viewModelScope.launch {
            list = db.getLastTR()
        }
    }

}