package com.example.coctails.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.domain2.entity.Recipe
import com.example.coctails.viewmodel.MyViewModel
import kotlinx.coroutines.delay

@Composable
fun MyApp(
    viewModel: MyViewModel
) {
    var showBottomNavi by remember {
        mutableStateOf(true)
    }
    var recipes by remember {
        mutableStateOf<List<Recipe>>(emptyList())
    }

    if (showBottomNavi) {
        var isVisible by remember {
            mutableStateOf(false)
        }
        LaunchedEffect(Unit) {
            delay(10)
            isVisible = true
            viewModel.allRecipes.collect {
                recipes = it
            }
        }
        AnimatedVisibility(
            visible = isVisible,
            modifier = Modifier
                .fillMaxWidth(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Greeting(
                viewModel = viewModel,
                onContinueClicked = { showBottomNavi = false },
            )
        }
    } else {
        var isVisible by remember {
            mutableStateOf(false)
        }
        LaunchedEffect(Unit) {
            delay(10)
            isVisible = true
        }
        AnimatedVisibility(
            visible = isVisible,
            modifier = Modifier
                .fillMaxWidth(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            AddCocktail(
                recipes = recipes,
                recipe = null,
                onIconClicked = { showBottomNavi = true },
                onCancelClick = { showBottomNavi = true },
                onSaveClick = { showBottomNavi = true },
                viewModel = viewModel
            )
        }
    }
}
