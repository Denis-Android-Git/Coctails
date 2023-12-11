package com.example.data.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain2.entity.Recipe

@Database(
    entities = [
        Recipe::class
               ],
    version = 9,
    exportSchema = false
)
@TypeConverters(Converters::class)

abstract class AppDataBase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}