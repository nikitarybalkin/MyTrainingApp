package com.example.myapplication2.presentation.utils

import androidx.room.TypeConverter

public class ResultsConverter {

    @TypeConverter
    public fun fromListIntToString(list: List<List<Int>>): String {
        return list.toString()
    }

    @TypeConverter
    public fun fromStringToListInt(string: String): List<List<Int>> {

        val result = ArrayList<List<Int>>()
        val split = string.replace("[", "")
            .replace("]", "")
            .replace(" ", "")
            .split(",")
        for (n in split) {
            try {
                result.add(listOf(n.toInt()))
            } catch (e: Exception) {

            }
        }
        return result

    }

}