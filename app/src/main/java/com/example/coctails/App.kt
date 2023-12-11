package com.example.coctails

import android.app.Application
import androidx.room.Room
import com.example.data.data.database.AppDataBase

class App : Application() {

    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "db"
        ).fallbackToDestructiveMigration().build()
    }
}