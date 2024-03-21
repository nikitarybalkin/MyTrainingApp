package com.example.myapplication2.viewModels

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.myapplication2.db.DatabaseModule
import com.example.myapplication2.fragments.CreateTrainFragment
import com.example.myapplication2.fragments.DetailedFragment
import com.example.myapplication2.fragments.MyTrainingsFragment
import com.example.myapplication2.fragments.ResultsDetailedFragment
import com.example.myapplication2.fragments.ResultsFragment
import com.example.myapplication2.fragments.TrainFragment
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

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}