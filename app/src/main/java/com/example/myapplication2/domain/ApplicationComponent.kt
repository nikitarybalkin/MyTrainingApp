package com.example.myapplication2.domain

import android.content.Context
import com.example.myapplication2.presentation.fragments.CreateTrainFragment
import com.example.myapplication2.presentation.fragments.DetailedFragment
import com.example.myapplication2.presentation.fragments.HomeFragment
import com.example.myapplication2.presentation.fragments.MyTrainingsFragment
import com.example.myapplication2.presentation.fragments.ResultsDetailedFragment
import com.example.myapplication2.presentation.fragments.ResultsFragment
import com.example.myapplication2.presentation.fragments.TrainFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DatabaseModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(fragment: CreateTrainFragment)
    fun inject(fragment: MyTrainingsFragment)
    fun inject(fragment: ResultsFragment)
    fun inject(fragment: DetailedFragment)
    fun inject(fragment: ResultsDetailedFragment)
    fun inject(fragment: TrainFragment)
    fun inject(fragment: HomeFragment)


    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}