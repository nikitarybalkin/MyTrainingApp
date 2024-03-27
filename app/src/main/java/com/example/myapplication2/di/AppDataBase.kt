package com.example.myapplication2.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication2.data.ResultsDao
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.data.TrainingDao
import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.presentation.utils.TrainsConverter

@Database(entities = [TrainingEntity::class, ResultsEntity::class], version = 8, exportSchema = false)
@TypeConverters(
    TrainsConverter::class,
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun exerciseDao() : TrainingDao
    abstract fun resultsDao() : ResultsDao
}