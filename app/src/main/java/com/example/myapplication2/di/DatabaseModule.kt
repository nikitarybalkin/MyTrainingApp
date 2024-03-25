package com.example.myapplication2.di

import android.content.Context
import androidx.room.Room
import com.example.myapplication2.data.TrainingDao
import com.example.myapplication2.data.ResultsDao
import com.example.myapplication2.data.ResultsRepositoryImpl
import com.example.myapplication2.data.TrainingRepositoryImpl
import com.example.myapplication2.domain.ResultsRepository
import com.example.myapplication2.domain.TrainingRepository
import dagger.Module
import dagger.Provides


@Module
class DatabaseModule {

    @Provides
    fun provideTrainingRepository(trainingRepositoryImpl: TrainingRepositoryImpl): TrainingRepository {
        return trainingRepositoryImpl
    }

    @Provides
    fun provideResultsRepository(resultsRepositoryImpl: ResultsRepositoryImpl): ResultsRepository {
        return resultsRepositoryImpl
    }

    @Provides
    fun provideExerciseBase(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, "db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideExerciseDao(db: AppDataBase): TrainingDao {
        return db.exerciseDao()
    }

    @Provides
    fun provideResultsDao(db: AppDataBase): ResultsDao {
        return db.resultsDao()
    }
}