package com.example.myapplication2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication2.domain.TrainsConverter

@Database(entities = [TrainingEntity::class, ResultsEntity::class], version = 6, exportSchema = false)
@TypeConverters(
    TrainsConverter::class,
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun exerciseDao() : ExerciseDao
    abstract fun resultsDao() : ResultsDao
}