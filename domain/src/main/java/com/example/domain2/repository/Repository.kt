package com.example.domain2.repository

import com.example.domain2.entity.Recipe
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun upsertRecipe(recipe: Recipe)

    fun getAllRecipes(): Flow<List<Recipe>>

    suspend fun deleteRecipe(recipe: Recipe)
}