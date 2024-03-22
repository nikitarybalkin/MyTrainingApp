package com.example.myapplication2.domain

import android.content.Context
import androidx.room.Room
import com.example.myapplication2.data.AppDataBase
import com.example.myapplication2.data.ExerciseDao
import com.example.myapplication2.data.ResultsDao
import dagger.Module
import dagger.Provides


@Module
class DatabaseModule {

    /*@Provides
    fun provideExerciseDao(): ExerciseDao

     */

    //@Singleton
    @Provides
    fun providerExerciseBase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "db")
            .fallbackToDestructiveMigration().build()
    }
    //@Singleton
    @Provides
    fun provideExerciseDao(db: AppDataBase): ExerciseDao {
        return db.exerciseDao()
    }
    //@Singleton
    @Provides
    fun provideResultsDao(db: AppDataBase): ResultsDao {
        return db.resultsDao()
    }
}