package com.example.fruitapp.search

import com.example.fruitapp.remote.FruitService
import javax.inject.Inject

class MainRepository @Inject constructor(var fruitInterface: FruitService) {
    suspend fun getAllShowDetail(fruitName: String) = fruitInterface.getAllDetails(fruitName)
}