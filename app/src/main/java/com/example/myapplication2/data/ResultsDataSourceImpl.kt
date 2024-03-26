package com.example.myapplication2.data

import com.example.myapplication2.data.dataSource.ResultsDataSource
import com.example.myapplication2.domain.model.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ResultsDataSourceImpl @Inject constructor(private val db: ResultsDao): ResultsDataSource {
    override fun getAll(): Flow<List<ResultsEntity>> {
        return db.getAll()
    }

    override fun getLastTR(): Flow<ResultsEntity> {
        return db.getLastTR()
    }

    /**
     * Функция, удаляющая конкретную запись из БД
     *
     * @param table запись, которую необходимо удалить
     */
    override suspend fun delete(table: ResultsEntity) {
        db.delete(table)
    }

    /**
     * Функция, добавляющая запись в БД
     *
     * @param table запись, которую необходимо добавить
     */
    override suspend fun insert(table: ResultsEntity) {
        db.insert(table)
    }

    /**
     * Функция, удаляющая все записи из БД
     *
     */
    override suspend fun deleteAll() {
        db.deleteAll()
    }
}