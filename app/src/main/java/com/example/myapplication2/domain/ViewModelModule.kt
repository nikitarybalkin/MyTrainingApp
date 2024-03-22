package com.example.myapplication2.domain

import androidx.lifecycle.ViewModel
import com.example.myapplication2.presentation.viewModels.CreateTrainViewModel
import com.example.myapplication2.presentation.viewModels.DetailedViewModel
import com.example.myapplication2.presentation.viewModels.HomeViewModel
import com.example.myapplication2.presentation.viewModels.MyTrainingsViewModel
import com.example.myapplication2.presentation.viewModels.ResultsDetailedViewModel
import com.example.myapplication2.presentation.viewModels.ResultsViewModel
import com.example.myapplication2.presentation.viewModels.TrainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey

@Module
interface ViewModelModule {

    @IntoMap
    @StringKey("TrainViewModel")
    @Binds
    fun bindsTrainViewModel(impl: TrainViewModel): ViewModel
    @IntoMap
    @StringKey("CreateTrainViewModel")
    @Binds
    fun bindsCreateTrainViewModel(impl: CreateTrainViewModel): ViewModel
    @IntoMap
    @StringKey("MyTrainingsViewModel")
    @Binds
    fun bindsMyTrainingsViewModel(impl: MyTrainingsViewModel): ViewModel
    @IntoMap
    @StringKey("ResultsViewModel")
    @Binds
    fun bindsResultsViewModel(impl: ResultsViewModel): ViewModel
    @IntoMap
    @StringKey("DetailedViewModel")
    @Binds
    fun bindsDetailedViewModel(impl: DetailedViewModel): ViewModel
    @IntoMap
    @StringKey("ResultsDetailedViewModel")
    @Binds
    fun bindsResultsDetailedViewModel(impl: ResultsDetailedViewModel): ViewModel
    @IntoMap
    @StringKey("HomeViewModel")
    @Binds
    fun bindsHomeViewModel(impl: HomeViewModel): ViewModel



}