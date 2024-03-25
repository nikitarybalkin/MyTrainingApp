package com.example.myapplication2.domain

import com.example.myapplication2.data.TrainingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrainingUseCase @Inject constructor(private val trainingRepository: TrainingRepository) {

    suspend fun insertTable(training: TrainingEntity) {

        trainingRepository.insertTable(training)

    }

    suspend fun delete(training: TrainingEntity) {

        trainingRepository.delete(training)

    }

    fun getAll(): Flow<List<TrainingEntity>> {

        return trainingRepository.getAll()

    }

    fun getOneTrainExercises(passedNameOfTrain: String): Flow<List<String>> {

        return trainingRepository.getOneTrainExercises(passedNameOfTrain)

    }

}