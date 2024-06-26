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