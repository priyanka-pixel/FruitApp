package com.example.fruitapp.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fruitapp.models.Fruits
import com.example.fruitapp.models.FruitsItem

@Dao
interface FruitsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(fruit: Fruits)
    
    @Query("SELECT*FROM Fruits_item")
    fun getAllFruits(): LiveData<List<FruitsItem>>

    /**
     * id detail
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(fruit: FruitsItem)

    @Query("SELECT * FROM Fruits_item WHERE id = :id")
    fun getFruit(id:Int): LiveData<FruitsItem>

    /**
     * name detail
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDetails(fruit: FruitsItem)

    @Query("SELECT*FROM Fruits_item WHERE name = :name")
    fun getAllDetails(name: String): LiveData<FruitsItem>

    /**
     * calorie
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNutritionDetails(fruit: FruitsItem)

    @Query("SELECT*FROM Fruits_item WHERE `order` = :order")
    fun getNutritionDetail(order: String): LiveData<FruitsItem>

    /**
     * Family call
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFamilyDetail(fruit: FruitsItem)

    @Query("SELECT*FROM fruits_item WHERE family= :family")
    fun getFamilyDetail(family: String): LiveData<FruitsItem>
}