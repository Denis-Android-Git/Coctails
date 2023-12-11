package com.example.data.data.database

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromIngredientsList(ingredients: SnapshotStateList<String>?): String {
        if (ingredients == null) {
            return ""
        }
        val gson = Gson()
        val type = object : TypeToken<SnapshotStateList<String>>() {}.type
        return gson.toJson(ingredients, type)
    }

    @TypeConverter
    fun toIngredientsList(ingredientsString: String): SnapshotStateList<String> {
        if (ingredientsString.isEmpty()) {
            return SnapshotStateList()
        }
        val gson = Gson()
        val type = object : TypeToken<SnapshotStateList<String>>() {}.type
        return gson.fromJson(ingredientsString, type)
    }
}
