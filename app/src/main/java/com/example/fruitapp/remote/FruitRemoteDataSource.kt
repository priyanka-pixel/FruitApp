package com.example.fruitapp.remote

import javax.inject.Inject

class FruitRemoteDataSource @Inject constructor(
    private val fruitService: FruitService
) : BaseDataSource() {
    suspend fun getFruits() = getResult {
        fruitService.getAllFruits()
    }

    // id
    suspend fun getFruitBYId(id: Int) =
        getResult { fruitService.getFruitsDetails(id) }

    // name
    suspend fun getFruitByName(name: String) =
        getResult { fruitService.getAllDetails(name) }

    // calorie
    suspend fun getNutritionByName(order: String) =
        getResult { fruitService.getNutritionDetail(order) }

    // family
    suspend fun getFamilyByName(family: String) =
        getResult { fruitService.getFamilyDetail(family) }
}
