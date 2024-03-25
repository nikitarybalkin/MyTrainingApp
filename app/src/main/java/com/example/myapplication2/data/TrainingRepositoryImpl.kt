package com.example.myapplication2.data

import com.example.myapplication2.domain.TrainingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TrainingRepositoryImpl @Inject constructor(private val db: TrainingDao): TrainingRepository {
    override suspend fun insertTable(table: TrainingEntity) {
        db.insertTable(table)
    }

    override suspend fun delete(training: TrainingEntity) {
        db.delete(training)
    }

    override fun getAll(): Flow<List<TrainingEntity>> {
        return db.getAll()
    }

    override fun getOneTrainExercises(passedNameOfTrain: String): Flow<List<String>> {

        return db.getOneTrainExercises(passedNameOfTrain)

    }

}