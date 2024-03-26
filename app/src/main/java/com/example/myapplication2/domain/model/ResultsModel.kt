package com.example.myapplication2.domain.model

import androidx.room.ColumnInfo
import com.example.myapplication2.data.ResultsEntity

class ResultsModel(
    val id: Int = 0,
    val nameOfTrain: String,
    val exercises: List<String>,
    val countInSet: List<String>,
    val countOfSets: List<String>,
    val weights: List<String>,
    val date: String,
    val time: String
)

fun ResultsEntity.mapToDomain() = ResultsModel(
    id,
    nameOfTrain,
    exercises,
    countInSet,
    countOfSets,
    weights,
    date,
    time
)

fun ResultsModel.mapToData() = ResultsEntity(
    id,
    nameOfTrain,
    exercises,
    countInSet,
    countOfSets,
    weights,
    date,
    time
)
