package com.example.coctails.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.coctails.viewmodel.MyViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyApp(
    viewModel: MyViewModel = koinViewModel()
) {
    var showBottomNavi by remember {
        mutableStateOf(true)
    }
    val recipes = viewModel.allRecipes.collectAsState()

    AnimatedVisibility(
        visible = showBottomNavi
    ) {
        Greeting(
            viewModel = viewModel,
            onContinueClicked = { showBottomNavi = false },
        )
    }
    AnimatedVisibility(visible = !showBottomNavi) {
        AddCocktail(
            viewModel = viewModel,
            recipes = recipes.value,
            recipe = null,
            onIconClicked = { showBottomNavi = true },
            onCancelClick = { showBottomNavi = true },
            onSaveClick = { showBottomNavi = true }
        )
    }
}
