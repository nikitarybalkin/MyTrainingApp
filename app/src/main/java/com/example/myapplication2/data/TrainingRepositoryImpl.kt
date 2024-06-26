package com.example.myapplication2.data

import com.example.myapplication2.domain.model.TrainingModel
import com.example.myapplication2.domain.model.mapToData
import com.example.myapplication2.domain.model.mapToDomain
import com.example.myapplication2.domain.repository.TrainingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TrainingRepositoryImpl @Inject constructor(private val db: TrainingDao): TrainingRepository {

    /**
     * Функция, добавляющая запись в БД
     *
     * @param table запись, которую необходимо добавить
     */
    override suspend fun insertTable(table: TrainingModel) {
        db.insertTable(table.mapToData())
    }

    /**
     * Функция, удаляющая конкретную запись из БД
     *
     * @param training запись, которую необходимо удалить
     */
    override suspend fun delete(training: TrainingModel) {
        db.delete(training.mapToData())
    }

    /**
     * Функция, возвращающая все значения из БД
     */
    override fun getAll(): Flow<List<TrainingModel>> {
        return db.getAll().map { list -> list.map { it.mapToDomain() } }
    }

    /**
     * Функция, возвращающая конкретную запись из БД
     *
     * @param passedNameOfTrain имя искомой записи из БД
     */
    override fun getOneTrainExercises(passedNameOfTrain: String): Flow<String> {
        return db.getOneTrainExercises(passedNameOfTrain)

    }

}