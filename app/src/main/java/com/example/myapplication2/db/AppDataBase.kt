package com.example.myapplication2.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication2.ResultsConverter
import com.example.myapplication2.TrainsConverter

@Database(entities = [TrainingEntity::class, ResultsEntity::class], version = 6)
@TypeConverters(
    TrainsConverter::class,
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun exerciseDao() : ExerciseDao
    abstract fun resultsDao() : ResultsDao
}