package com.example.coctails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.coctails.presentation.MyApp
import com.example.coctails.viewmodel.MyViewModel
import com.example.coctails.viewmodel.MyViewModelFactory
import com.example.coctails.ui.theme.CoctailsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recipeDao = (application as App).db.recipeDao()

        val viewModel: MyViewModel by viewModels {
            MyViewModelFactory(recipeDao)
        }
        setContent {
            CoctailsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(viewModel)
                }
            }
        }
    }
}