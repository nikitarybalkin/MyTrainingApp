package com.example.myapplication2.di

import androidx.lifecycle.ViewModel
import com.example.myapplication2.presentation.viewModel.CreateTrainViewModel
import com.example.myapplication2.presentation.viewModel.DetailedViewModel
import com.example.myapplication2.presentation.viewModel.HomeViewModel
import com.example.myapplication2.presentation.viewModel.MyTrainingsViewModel
import com.example.myapplication2.presentation.viewModel.ResultsDetailedViewModel
import com.example.myapplication2.presentation.viewModel.ResultsViewModel
import com.example.myapplication2.presentation.viewModel.TrainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.StringKey
import javax.inject.Singleton

@Module
interface ViewModelModule {

    @Singleton
    @IntoMap
    @StringKey("TrainViewModel")
    @Binds
    fun bindsTrainViewModel(impl: TrainViewModel): ViewModel
    @Singleton
    @IntoMap
    @StringKey("CreateTrainViewModel")
    @Binds
    fun bindsCreateTrainViewModel(impl: CreateTrainViewModel): ViewModel
    @Singleton
    @IntoMap
    @StringKey("MyTrainingsViewModel")
    @Binds
    fun bindsMyTrainingsViewModel(impl: MyTrainingsViewModel): ViewModel
    @Singleton
    @IntoMap
    @StringKey("ResultsViewModel")
    @Binds
    fun bindsResultsViewModel(impl: ResultsViewModel): ViewModel
    @Singleton
    @IntoMap
    @StringKey("DetailedViewModel")
    @Binds
    fun bindsDetailedViewModel(impl: DetailedViewModel): ViewModel
    @Singleton
    @IntoMap
    @StringKey("ResultsDetailedViewModel")
    @Binds
    fun bindsResultsDetailedViewModel(impl: ResultsDetailedViewModel): ViewModel
    @Singleton
    @IntoMap
    @StringKey("HomeViewModel")
    @Binds
    fun bindsHomeViewModel(impl: HomeViewModel): ViewModel
}