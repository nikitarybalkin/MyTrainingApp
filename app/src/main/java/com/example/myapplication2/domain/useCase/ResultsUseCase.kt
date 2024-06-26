package com.example.myapplication2.domain.useCase

import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.data.ResultsRepositoryImpl
import com.example.myapplication2.domain.model.ResultsModel
import com.example.myapplication2.domain.repository.ResultsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResultsUseCase @Inject constructor(private val resultsRepositoryImpl: ResultsRepositoryImpl) {
    fun getAll(): Flow<List<ResultsModel?>> {
        return resultsRepositoryImpl.getAll()
    }

    fun getLastTR(): Flow<List<ResultsModel?>> {
        return resultsRepositoryImpl.getLastTR()
    }

    suspend fun delete(table: ResultsModel) {
        resultsRepositoryImpl.delete(table)
    }

    suspend fun insert(table: ResultsModel) {
        resultsRepositoryImpl.insert(table)
    }

    suspend fun deleteAll() {
        resultsRepositoryImpl.deleteAll()
    }

}