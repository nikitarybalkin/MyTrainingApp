package com.example.myapplication2.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDao {

    @Query("SELECT * FROM trainingTable")
    fun getAll(): Flow<List<TrainingEntity>>

    @Query("SELECT exercise FROM trainingTable WHERE nameOfTrain = :passedNameOfTrain123")
    fun getOneTrainExercises(passedNameOfTrain123: String): Flow<List<String>>

    @Delete
    suspend fun delete(table: TrainingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTable(tr: TrainingEntity)

    @Query("DELETE FROM trainingTable")
    suspend fun deleteAll()

}