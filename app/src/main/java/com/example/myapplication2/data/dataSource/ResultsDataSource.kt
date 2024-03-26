package com.example.myapplication2.data.dataSource

import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.domain.model.ResultsModel
import kotlinx.coroutines.flow.Flow

interface ResultsDataSource {
    fun getAll(): Flow<List<ResultsEntity>>

    fun getLastTR(): Flow<ResultsEntity>

    suspend fun delete(table: ResultsEntity)

    suspend fun insert(table: ResultsEntity)

    suspend fun deleteAll()
}