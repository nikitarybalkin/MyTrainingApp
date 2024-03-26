package com.example.myapplication2.domain.useCase

import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.domain.model.TrainingModel
import com.example.myapplication2.domain.repository.TrainingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrainingUseCase @Inject constructor(private val trainingRepository: TrainingRepository) {

    suspend fun insertTable(training: TrainingModel) {

        trainingRepository.insertTable(training)

    }

    suspend fun delete(training: TrainingModel) {

        trainingRepository.delete(training)

    }

    fun getAll(): Flow<List<TrainingModel>> {

        return trainingRepository.getAll()

    }

    fun getOneTrainExercises(passedNameOfTrain: String): Flow<String> {

        return trainingRepository.getOneTrainExercises(passedNameOfTrain)

    }

}