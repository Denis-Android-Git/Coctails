package com.example.coctails.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.coctails.R
import com.example.domain2.entity.Recipe

@Composable
fun Item(
    recipe: Recipe,
    onItemClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(10.dp)
            .clickable(
                onClick = onItemClick
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
    }
}
//
//@Preview
//@Composable
//fun PreviewItem() {
//    CoctailsTheme {
//        Item()
//    }
//}