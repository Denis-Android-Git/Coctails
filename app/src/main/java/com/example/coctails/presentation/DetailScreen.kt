package com.example.coctails.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.coctails.R
import com.example.coctails.viewmodel.MyViewModel
import com.example.domain2.entity.Recipe

@Composable
fun DetailScreen(
    recipe: Recipe,
    onEditClick: () -> Unit,
    onBackClick: () -> Unit,
    onDeleteClick: () -> Unit,
    viewModel: MyViewModel
) {

    var showConformation by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (recipe.image.isEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.oldfashioned),
                contentDescription = null,
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        } else {
            AsyncImage(
                model = recipe.image,
                contentDescription = null,
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(6.dp)
                .size(38.dp)
                .background(color = Color.White, shape = CircleShape)
                .clickable {
                    showConformation = true
                }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "delete recipe",
                tint = Color.Black,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        if (showConformation) {
            AlertDialog(
                onDismissRequest = { showConformation = false },
                text = { Text("Are you sure?") },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.deleteRecipe(recipe = recipe)
                            showConformation = false
                            onDeleteClick()
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray
                        )
                    ) {
                        Text(text = "Delete", color = Color.DarkGray)
                    }
                },
                dismissButton = {
                    Button(
                        onClick = { showConformation = false },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(8.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.LightGray
                        )
                    ) {
                        Text(text = "Cancel", color = Color.DarkGray)
                    }
                }
            )
        }

        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 300.dp),
            shape = RoundedCornerShape(40.dp)
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
            ) {
                Text(
                    text = recipe.title,
                    fontSize = 32.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                if (recipe.recipe?.isNotEmpty() == true) {
                    Text(
                        text = recipe.description!!,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 6.dp, bottom = 6.dp),
                    )
                }
                if (recipe.ingredients!!.isNotEmpty()) {
                    Text(
                        text = "Ingredients:",
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = recipe.ingredients!!.joinToString("\n") {
                            it
                        },
                        fontSize = 16.sp,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(6.dp),
                    )
                }

                if (recipe.recipe!!.isNotEmpty()) {
                    Text(
                        text = "Recipe:",
                        fontSize = 20.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 16.dp)
                    )
                    Text(
                        text = recipe.recipe!!,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 6.dp, bottom = 6.dp),
                    )
                }

                Button(
                    onClick = onEditClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    Text(text = "Edit")
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 6.dp)
                            .size(16.dp)
                    )
                }
            }
        }
        Back(
            onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(6.dp)
                .size(38.dp)
                .background(color = Color.White, shape = CircleShape)
        )
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun DetailPreview() {
//    CoctailsTheme {
//        val onEditClick: () -> Unit = {}
//        DetailScreen(onEditClick)
//    }
//}