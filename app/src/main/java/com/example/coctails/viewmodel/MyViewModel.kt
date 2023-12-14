package com.example.coctails.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain2.entity.Recipe
import com.example.domain2.usecase.UseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MyViewModel(
    private val useCase: UseCase
) : ViewModel() {

    val allRecipes = useCase.getRecipes()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun addRecipe(
        id: Int,
        title: String,
        description: String?,
        recipe: String?,
        ingredients: SnapshotStateList<String>?,
        image: String
    ) {
        viewModelScope.launch {
            useCase.addRecipe(
                Recipe(
                    id = id,
                    title = title,
                    description = description,
                    recipe = recipe,
                    ingredients = ingredients,
                    image = image
                )
            )
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            useCase.deleteRecipe(recipe)
        }
    }
}