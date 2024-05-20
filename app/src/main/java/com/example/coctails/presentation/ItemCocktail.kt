package com.example.coctails.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.coctails.R
import com.example.coctails.ui.theme.CoctailsTheme
import com.example.domain2.entity.Recipe

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Item(
    recipe: Recipe,
    listToDelete: MutableList<Recipe>,
    showCheckBox: Boolean,
    onItemClick: () -> Unit,
    onLongClick: () -> Unit

) {

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(10.dp)
            .combinedClickable(
                onClick = onItemClick,
                onLongClick = {
                    onLongClick()
                    listToDelete.add(recipe)
                }
            )
            .shadow(12.dp, RoundedCornerShape(20.dp))
    ) {
        val painter = rememberAsyncImagePainter(model = recipe.image)

        if (recipe.image.isEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.oldfashioned),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(30.dp)),
                contentScale = ContentScale.Crop
            )
        } else {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(30.dp)),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = recipe.title,
            fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
        )
        AnimatedVisibility(visible = showCheckBox) {
            Checkbox(
                checked = listToDelete.contains(recipe),
                onCheckedChange = {
                    if (listToDelete.contains(recipe)) {
                        listToDelete.remove(recipe)
                    } else {
                        listToDelete.add(recipe)
                    }
                },
                colors = CheckboxColors(
                    checkedBorderColor = Color.White,
                    checkedBoxColor = Color.White,
                    checkedCheckmarkColor = Color.Black,
                    disabledBorderColor = Color.Transparent,
                    disabledCheckedBoxColor = Color.Transparent,
                    disabledIndeterminateBorderColor = Color.Transparent,
                    disabledUncheckedBoxColor = Color.Transparent,
                    disabledUncheckedBorderColor = Color.Transparent,
                    disabledIndeterminateBoxColor = Color.Transparent,
                    uncheckedBorderColor = Color.White,
                    uncheckedBoxColor = Color.White,
                    uncheckedCheckmarkColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewItem() {
    CoctailsTheme {
        val list by remember {
            mutableStateOf(mutableStateListOf("One"))
        }
        val item = Recipe(
            description = "test",
            id = 1,
            image = "",
            ingredients = list,
            recipe = "two",
            title = "Great!"
        )
        val list2 = mutableListOf(item)

        Item(
            item,
            onItemClick = {},
            listToDelete = list2,
            onLongClick = {},
            showCheckBox = true
        )
    }
}