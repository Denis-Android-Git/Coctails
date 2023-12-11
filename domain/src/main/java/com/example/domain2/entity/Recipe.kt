package com.example.domain2.entity

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "recipe")
    val recipe: String?,

    @ColumnInfo(name = "ingredients")
    val ingredients: SnapshotStateList<String>?
)
