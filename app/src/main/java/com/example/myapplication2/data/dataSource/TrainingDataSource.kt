package com.example.myapplication2.data.dataSource

import com.example.myapplication2.data.TrainingEntity
import kotlinx.coroutines.flow.Flow

interface TrainingDataSource {
    suspend fun insertTable(table: TrainingEntity)

    suspend fun delete(training: TrainingEntity)

    fun getAll(): Flow<List<TrainingEntity>>

    fun getOneTrainExercises(passedNameOfTrain: String): Flow<String>
}