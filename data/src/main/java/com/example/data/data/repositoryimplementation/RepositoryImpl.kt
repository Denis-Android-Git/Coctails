package com.example.data.data.repositoryimplementation

import com.example.data.data.database.AppDataBase
import com.example.domain2.entity.Recipe
import com.example.domain2.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val appDataBase: AppDataBase
) : Repository {
    override suspend fun upsertRecipe(recipe: Recipe) {
        appDataBase.recipeDao().upsertRecipe(recipe)
    }

    override fun getAllRecipes(): Flow<List<Recipe>> {
        return appDataBase.recipeDao().getAllRecipes()
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        appDataBase.recipeDao().deleteRecipe(recipe)
    }
}