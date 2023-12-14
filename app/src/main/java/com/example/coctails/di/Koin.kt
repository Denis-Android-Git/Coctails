package com.example.coctails.di

import androidx.room.Room
import com.example.coctails.viewmodel.MyViewModel
import com.example.data.data.database.AppDataBase
import com.example.data.data.repositoryimplementation.RepositoryImpl
import com.example.domain2.repository.Repository
import com.example.domain2.usecase.UseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    single<Repository> { RepositoryImpl(get()) }
    factory { UseCase(get()) }
    viewModel { MyViewModel(get()) }
}

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDataBase::class.java,
            "db"
        ).fallbackToDestructiveMigration().build()
    }
}
val allModules = listOf(dataModule, module)

