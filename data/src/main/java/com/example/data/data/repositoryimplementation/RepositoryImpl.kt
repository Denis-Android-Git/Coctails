package com.example.data.data.repositoryimplementation

import com.example.data.data.database.RecipeDao
import com.example.domain2.entity.Recipe
import com.example.domain2.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val dao: RecipeDao
) : Repository {
    override suspend fun upsertRecipe(recipe: Recipe) {
        dao.upsertRecipe(recipe)
    }

    override fun getAllRecipes(): Flow<List<Recipe>> {
        return dao.getAllRecipes()
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        dao.deleteRecipe(recipe)
    }
}