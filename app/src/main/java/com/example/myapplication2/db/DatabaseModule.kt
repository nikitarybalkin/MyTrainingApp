package com.example.myapplication2.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    /*@Provides
    fun provideExerciseDao(): ExerciseDao

     */

    //@Singleton
    @Provides
    fun providerExerciseBase(context: Context): AppDataBase{
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