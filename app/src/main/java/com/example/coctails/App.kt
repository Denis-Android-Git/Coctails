package com.example.coctails

import android.app.Application
import androidx.room.Room
import com.example.coctails.di.allModules
import com.example.data.data.database.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    lateinit var db: AppDataBase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "db"
        ).fallbackToDestructiveMigration().build()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(allModules)
        }
    }
}