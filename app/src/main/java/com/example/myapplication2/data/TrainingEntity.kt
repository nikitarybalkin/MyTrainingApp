package com.example.myapplication2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trainingTable")
data class TrainingEntity(

    @PrimaryKey
    @ColumnInfo(name = "nameOfTrain")
    val nameOfTrainingEntity: String,
    @ColumnInfo("exercise")
    val exercises: List<String>?,

)
