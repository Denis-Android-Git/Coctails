package com.example.domain2.usecase

import com.example.domain2.entity.Recipe
import com.example.domain2.repository.Repository
import kotlinx.coroutines.flow.Flow

class UseCase(
    private val repository: Repository
) {
    suspend fun deleteRecipe(recipe: Recipe) {
        repository.deleteRecipe(recipe)
    }

    fun getRecipes(): Flow<List<Recipe>> {
        return repository.getAllRecipes()
    }

    suspend fun addRecipe(recipe: Recipe) {
        repository.upsertRecipe(recipe)
    }
}