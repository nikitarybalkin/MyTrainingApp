package com.example.myapplication2.domain.repository

import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.domain.model.TrainingModel
import kotlinx.coroutines.flow.Flow

interface TrainingRepository {

    suspend fun insertTable(table: TrainingModel)

    suspend fun delete(training: TrainingModel)

    fun getAll(): Flow<List<TrainingModel>>

    fun getOneTrainExercises(passedNameOfTrain: String): Flow<String>


}