package com.example.myapplication2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.myapplication2.TrainsConverter

@Entity(tableName = "trainingTable")
data class TrainingEntity(

    @PrimaryKey
    @ColumnInfo(name = "nameOfTrain")
    val nameOfTrainingEntity: String,
    @ColumnInfo("exercise")
    val exercises: List<String>?,

)
