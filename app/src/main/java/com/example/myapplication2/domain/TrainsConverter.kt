package com.example.myapplication2.domain

import androidx.room.TypeConverter
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