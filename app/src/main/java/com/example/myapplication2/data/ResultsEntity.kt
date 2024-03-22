package com.example.myapplication2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resultsTable")
data class ResultsEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val id: Int = 0,
    @ColumnInfo(name = "nameOfTrain")
    val nameOfTrain: String,
    @ColumnInfo(name = "exercises")
    val exercises: List<String>,
    @ColumnInfo(name = "countInSet")
    val countInSet: List<String>,
    @ColumnInfo(name = "countOfSets")
    val countOfSets: List<String>,
    @ColumnInfo(name = "weights")
    val weights: List<String>,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "time")
    val time: String

)
