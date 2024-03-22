package com.example.myapplication2.domain

import android.app.Application
import com.example.myapplication2.viewModels.DaggerApplicationComponent


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
