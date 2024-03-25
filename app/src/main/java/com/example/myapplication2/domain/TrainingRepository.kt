package com.example.myapplication2.domain

import com.example.myapplication2.data.TrainingEntity
import kotlinx.coroutines.flow.Flow

interface TrainingRepository {

    suspend fun insertTable(table: TrainingEntity)

    suspend fun delete(training: TrainingEntity)

    fun getAll(): Flow<List<TrainingEntity>>

    fun getOneTrainExercises(passedNameOfTrain: String): Flow<List<String>>


}