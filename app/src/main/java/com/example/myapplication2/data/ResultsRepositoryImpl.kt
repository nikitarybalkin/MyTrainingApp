package com.example.myapplication2.data

import com.example.myapplication2.data.dataSource.ResultsDataSource
import com.example.myapplication2.domain.model.ResultsModel
import com.example.myapplication2.domain.model.mapToData
import com.example.myapplication2.domain.model.mapToDomain
import com.example.myapplication2.domain.repository.ResultsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ResultsRepositoryImpl @Inject constructor(private val ds: ResultsDataSource): ResultsRepository {

    /**
     * Функция, возвращающая все значения из БД
     */
    override fun getAll(): Flow<List<ResultsModel>> {
        return ds.getAll().map { list -> list.map { it.mapToDomain() } }
    }

    /**
     * Функция, возвращающая последнюю запись из БД
     */
    override fun getLastTR(): Flow<ResultsModel> {
        return ds.getLastTR().map { it.mapToDomain() }
    }

    /**
     * Функция, удаляющая конкретную запись из БД
     *
     * @param table запись, которую необходимо удалить
     */
    override suspend fun delete(table: ResultsModel) {
        ds.delete(table.mapToData())
    }

    /**
     * Функция, добавляющая запись в БД
     *
     * @param table запись, которую необходимо добавить
     */
    override suspend fun insert(table: ResultsModel) {
        ds.insert(table.mapToData())
    }

    /**
     * Функция, удаляющая все записи из БД
     *
     */
    override suspend fun deleteAll() {
        ds.deleteAll()
    }
}
