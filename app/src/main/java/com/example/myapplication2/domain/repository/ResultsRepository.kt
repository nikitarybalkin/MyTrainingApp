package com.example.myapplication2.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.domain.model.ResultsModel
import kotlinx.coroutines.flow.Flow

interface ResultsRepository {

    fun getAll(): Flow<List<ResultsModel>>

    fun getLastTR(): Flow<ResultsModel>

    suspend fun delete(table: ResultsModel)

    suspend fun insert(table: ResultsModel)

    suspend fun deleteAll()
}