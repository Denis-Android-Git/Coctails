package com.example.coctails.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coctails.R
import com.example.coctails.viewmodel.MyViewModel
import com.example.domain2.entity.Recipe
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Greeting(
    viewModel: MyViewModel = koinViewModel(),
    onContinueClicked: () -> Unit
) {

    var recipeList by remember {
        mutableStateOf(emptyList<Recipe>())
    }

    var showDetailScreen by remember {
        mutableStateOf(false)
    }
    var showEditScreen by remember {
        mutableStateOf(false)
    }

    var showGreetingScreen by remember {
        mutableStateOf(true)
    }

    var recipe by remember {
        mutableStateOf<Recipe?>(null)
    }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.allRecipes.collect {
            recipeList = it
        }

    }

    if (showGreetingScreen) {

        val fabShape = RoundedCornerShape(50)

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
            Scaffold(
                backgroundColor = MaterialTheme.colorScheme.background,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = onContinueClicked,
                        shape = fabShape,
                        backgroundColor = Color(0xFF4B97FF)
                    ) {
                        Icon(Icons.Default.Add, "", tint = Color.White)
                    }
                },
                isFloatingActionButtonDocked = true,
                floatingActionButtonPosition = FabPosition.Center,
                bottomBar = {
                    BottomAppBar(
                        cutoutShape = fabShape,
                        content = {},
                        backgroundColor = Color.White,
                        modifier = Modifier
                            .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                    )
                }
            ) { _ ->
                if (recipeList.isEmpty()) {
                    Column(modifier = Modifier.fillMaxWidth()) {

                        Image(
                            painter = painterResource(id = R.drawable.img),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 100.dp)
                        )

                        Text(
                            text = "My Cocktails",
                            fontSize = 36.sp,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(46.dp))
                        Text(
                            text = "Add your first cocktail here",
                            fontSize = 16.sp,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(46.dp))
                        Image(
                            painter = painterResource(id = R.drawable.img_1),
                            contentDescription = null,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(46.dp))
                    }
                } else {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.padding(6.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = "My Cocktails",
                                    fontSize = 36.sp,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(6.dp)
                                        .size(38.dp)
                                        .background(color = Color.White, shape = CircleShape)
                                        .clickable {
                                            val sendIntent: Intent = Intent().apply {
                                                action = Intent.ACTION_SEND
                                                putExtra(
                                                    Intent.EXTRA_TEXT,
                                                    "Смотри какие коктейли я создал в приложении Cocktail Bar!\n" +
                                                            "${
                                                                recipeList
                                                                    .take(4)
                                                                    .joinToString(", ") {
                                                                        it.title
                                                                    }
                                                            } и другие." +
                                                            "Хочешь попробовать?\n"
                                                )
                                                type = "text/plain"
                                            }
                                            val shareIntent = Intent.createChooser(sendIntent, null)
                                            context.startActivity(shareIntent)
                                        }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Share,
                                        contentDescription = "delete recipe",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                    )
                                }

                            }
                            LazyVerticalGrid(
                                columns = GridCells.Adaptive(minSize = 120.dp),
                                state = rememberLazyGridState(),
                                contentPadding = PaddingValues(20.dp)
                            ) {
                                items(
                                    recipeList,
                                    key = {
                                        it.id
                                    }
                                ) {
                                    Log.d("tag", "recipeList: ${recipeList.joinToString("/")}")
                                    Item(recipe = it) {
                                        showDetailScreen = true
                                        showGreetingScreen = false
                                        recipe = it
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    if (showDetailScreen) {
        var isVisible2 by remember {
            mutableStateOf(false)
        }
        LaunchedEffect(Unit) {
            delay(10)
            isVisible2 = true
        }

        AnimatedVisibility(
            visible = isVisible2,
            modifier = Modifier
                .fillMaxWidth(),
            enter = fadeIn(animationSpec = tween(durationMillis = 600, easing = LinearEasing)),
            exit = fadeOut(animationSpec = tween(durationMillis = 600))
        ) {
            recipe?.let {
                DetailScreen(
                    recipe = it,
                    //viewModel = viewModel,
                    onEditClick = {
                        showDetailScreen = false
                        showGreetingScreen = false
                        showEditScreen = true
                    },
                    onBackClick = {
                        showDetailScreen = false
                        showGreetingScreen = true
                    },
                    onDeleteClick = {
                        showDetailScreen = false
                        showGreetingScreen = true
                    }
                )
            }
        }
    }
    if (showEditScreen) {
        recipe?.let {
            AddCocktail(
                recipes = recipeList,
                recipe = it,
                onIconClicked = {
                    showEditScreen = false
                    showDetailScreen = true
                },
                onCancelClick = {
                    showEditScreen = false
                    showGreetingScreen = true
                },
                onSaveClick = {
                    showEditScreen = false
                    showGreetingScreen = true
                },
                viewModel = viewModel
            )
        }
    }
}

