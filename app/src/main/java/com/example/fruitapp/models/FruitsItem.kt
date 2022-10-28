package com.example.fruitapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Fruits_item")
data class FruitsItem(
    val family: String,
    val genus: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val order: String,
)
