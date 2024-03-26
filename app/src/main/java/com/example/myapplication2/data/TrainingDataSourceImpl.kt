package com.example.myapplication2.data

import com.example.myapplication2.data.dataSource.TrainingDataSource
import com.example.myapplication2.domain.model.ResultsModel
import com.example.myapplication2.domain.model.mapToData
import com.example.myapplication2.domain.model.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TrainingDataSourceImpl @Inject constructor(private val db: TrainingDao): TrainingDataSource {
    /**
     * Функция, добавляющая запись в БД
     *
     * @param table запись, которую необходимо добавить
     */
    override suspend fun insertTable(table: TrainingEntity) {
        db.insertTable(table)
    }

    /**
     * Функция, удаляющая конкретную запись из БД
     *
     * @param training запись, которую необходимо удалить
     */
    override suspend fun delete(training: TrainingEntity) {
        db.delete(training)
    }

    /**
     * Функция, возвращающая все значения из БД
     */
    override fun getAll(): Flow<List<TrainingEntity>> {
        return db.getAll()
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