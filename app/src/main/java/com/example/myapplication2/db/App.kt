package com.example.myapplication2.db

import android.app.Application
import androidx.room.Room

class App : Application() {

    lateinit var db : AppDataBase

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "db"
        ).fallbackToDestructiveMigration().build()
    }
}