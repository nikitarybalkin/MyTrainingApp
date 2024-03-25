package com.example.myapplication2.data

import com.example.myapplication2.domain.ResultsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResultsRepositoryImpl @Inject constructor(private val db: ResultsDao): ResultsRepository {
    override fun getAll(): Flow<List<ResultsEntity>> {
        return db.getAll()
    }

    override fun getLastTR(): Flow<List<ResultsEntity>> {
        return db.getLastTR()
    }

    override suspend fun delete(table: ResultsEntity) {
        db.delete(table)
    }

    override suspend fun insert(table: ResultsEntity) {
        db.insert(table)
    }

    override suspend fun deleteAll() {
        db.deleteAll()
    }
}