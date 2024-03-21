package com.example.myapplication2.fragments

import com.example.myapplication2.db.ExerciseDao
import com.example.myapplication2.db.TrainingEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ExerciseDaoUseCase @Inject constructor(
): ExerciseDao {
    override fun getAll(): Flow<List<TrainingEntity>> {
        TODO("Not yet implemented")
    }

    override fun getOneTrainExercises(passedNameOfTrain123: String): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(table: TrainingEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun insertTable(tr: TrainingEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }
}