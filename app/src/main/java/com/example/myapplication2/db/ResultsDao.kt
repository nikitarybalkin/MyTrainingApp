package com.example.myapplication2.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultsDao {

    @Query("SELECT * FROM resultsTable")
    fun getAll(): Flow<List<ResultsEntity>>

    @Delete
    suspend fun delete(table: ResultsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(table: ResultsEntity)

    @Query("DELETE FROM resultsTable")
    suspend fun deleteAll()

}