package com.example.myapplication2.domain

import android.app.Application



class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}
