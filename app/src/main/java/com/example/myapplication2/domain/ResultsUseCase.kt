package com.example.myapplication2.domain

import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.data.ResultsRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResultsUseCase @Inject constructor(private val resultsRepositoryImpl: ResultsRepositoryImpl): ResultsRepository {
    override fun getAll(): Flow<List<ResultsEntity>> {
        return resultsRepositoryImpl.getAll()
    }

    override fun getLastTR(): Flow<List<ResultsEntity>> {
        return resultsRepositoryImpl.getLastTR()
    }

    override suspend fun delete(table: ResultsEntity) {
        resultsRepositoryImpl.delete(table)
    }

    override suspend fun insert(table: ResultsEntity) {
        resultsRepositoryImpl.insert(table)
    }

    override suspend fun deleteAll() {
        resultsRepositoryImpl.deleteAll()
    }

}