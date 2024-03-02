package com.example.myapplication2.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.db.ResultsDao
import com.example.myapplication2.db.ResultsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ResultsViewModel(val resultsDao: ResultsDao) : ViewModel() {
    var listOfExers: Flow<List<ResultsEntity>>? = MutableStateFlow(emptyList())
    fun getAll() {
        viewModelScope.launch {

            listOfExers = resultsDao.getAll()

        }
    }

}