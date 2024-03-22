package com.example.myapplication2.data

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

    @Query("SELECT * \n" +
            "    FROM resultsTable\n" +
            "    WHERE id = (SELECT MAX(id) FROM resultsTable);")
    fun getLastTR(): Flow<List<ResultsEntity>>

    @Delete
    suspend fun delete(table: ResultsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(table: ResultsEntity)

    @Query("DELETE FROM resultsTable")
    suspend fun deleteAll()

}