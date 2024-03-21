package com.example.myapplication2.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.db.ResultsDao
import com.example.myapplication2.db.ResultsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ResultsDetailedViewModel @Inject constructor(private val resDao: ResultsDao) : ViewModel() {
    var resList: Flow<List<ResultsEntity>> = MutableStateFlow(emptyList())
    fun getAll() {
        viewModelScope.launch {
            resList = resDao.getAll()
        }
    }

}