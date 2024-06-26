package com.example.myapplication2.presentation.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.data.TrainingEntity
import com.example.myapplication2.domain.model.TrainingModel
import com.example.myapplication2.domain.useCase.TrainingUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CreateTrainViewModel @Inject constructor(
    private val trainingRepositoryImpl: TrainingUseCase
) : ViewModel() {

    fun insert(train: TrainingModel) {

        viewModelScope.launch {

            trainingRepositoryImpl.insertTable(train)
            Log.d(TAG, "readytable = $train")

        }

    }

}