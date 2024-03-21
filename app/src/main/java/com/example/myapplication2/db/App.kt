package com.example.myapplication2.db

import android.app.Application
import androidx.room.Room
import com.example.myapplication2.viewModels.ApplicationComponent
import com.example.myapplication2.viewModels.DaggerApplicationComponent
import dagger.android.DaggerApplication


class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    //lateinit var db : AppDataBase
    /*
        override fun onCreate() {
            super.onCreate()
            db = Room.databaseBuilder(
                applicationContext,
                AppDataBase::class.java,
                "db"
            ).fallbackToDestructiveMigration().build()

             */
    }
