package com.example.fruitapp.remote

import com.example.fruitapp.models.Fruits
import com.example.fruitapp.models.FruitsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FruitService {
    @GET("fruit/all")
    suspend fun getAllFruits():Response<Fruits>

    //append ID to the path of the URL
    @GET("fruits/{id}")
    suspend fun getFruitsDetails(@Path("id") id: Int): Response<FruitsItem>

//name path
    @GET("fruit/{name}")
    suspend fun getAllDetails(@Path("name")name: String): Response<FruitsItem>
//calorie path
    @GET("/fruit/{order}")
    suspend fun getNutritionDetail(@Path("order") order: String): Response<FruitsItem>

//family path
    @GET("fruit/{family}")
    suspend fun getFamilyDetail(@Path("family") family: String): Response<FruitsItem>

}