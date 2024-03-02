package com.example.myapplication2

import androidx.room.TypeConverter
import java.util.Arrays
import java.util.stream.Collectors

public class TrainsConverter {

    @TypeConverter
    public fun toDB(l1: List<String>?): String? {

        return l1?.stream()?.collect(Collectors.joining(","))

    }

    @TypeConverter
    public fun fromDB(l1: String?): List<String>? {

        return l1?.split(",")

    }

}