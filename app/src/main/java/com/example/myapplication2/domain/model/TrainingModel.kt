package com.example.myapplication2.domain.model

import androidx.room.ColumnInfo
import com.example.myapplication2.data.ResultsEntity
import com.example.myapplication2.data.TrainingEntity

data class TrainingModel(
    val nameOfTrainingEntity: String,
    val exercises: List<String>?,
)

fun TrainingEntity.mapToDomain() = TrainingModel(
    nameOfTrainingEntity,
    exercises
)

fun TrainingModel.mapToData() = TrainingEntity(
    nameOfTrainingEntity,
    exercises
)