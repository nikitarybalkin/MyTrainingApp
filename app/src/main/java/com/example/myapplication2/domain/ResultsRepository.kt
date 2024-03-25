package com.example.myapplication2.domain

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication2.data.ResultsEntity
import kotlinx.coroutines.flow.Flow

interface ResultsRepository {

    fun getAll(): Flow<List<ResultsEntity>>

    fun getLastTR(): Flow<List<ResultsEntity>>

    suspend fun delete(table: ResultsEntity)

    suspend fun insert(table: ResultsEntity)

    suspend fun deleteAll()
}