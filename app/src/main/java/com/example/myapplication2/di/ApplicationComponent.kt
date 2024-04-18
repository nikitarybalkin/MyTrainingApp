package com.example.myapplication2.di

import android.content.Context
import com.example.myapplication2.presentation.fragment.CreateTrainFragment
import com.example.myapplication2.presentation.fragment.DetailedFragment
import com.example.myapplication2.presentation.fragment.HomeFragment
import com.example.myapplication2.presentation.fragment.MyTrainingsFragment
import com.example.myapplication2.presentation.fragment.ResultsDetailedFragment
import com.example.myapplication2.presentation.fragment.ResultsFragment
import com.example.myapplication2.presentation.fragment.TrainBottomSheetFragment
import com.example.myapplication2.presentation.fragment.TrainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(fragment: CreateTrainFragment)
    fun inject(fragment: MyTrainingsFragment)
    fun inject(fragment: ResultsFragment)
    fun inject(fragment: DetailedFragment)
    fun inject(fragment: ResultsDetailedFragment)
    fun inject(fragment: TrainFragment)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: TrainBottomSheetFragment)


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}