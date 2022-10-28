package com.example.fruitapp.repository


import com.example.fruitapp.local.FruitsDAO
import com.example.fruitapp.remote.FruitRemoteDataSource
import com.example.fruitapp.utils.performGetOperation
import javax.inject.Inject

/**
 * centralized location: data source which will be given to UI
 *database strategy
 */
class FruitRepository @Inject constructor(
    private val remoteDataSource: FruitRemoteDataSource,
    private val localDataSource: FruitsDAO
){
    fun getFruits() = performGetOperation(
        databaseQuery =  {localDataSource.getAllFruits()},
        networkCall = {remoteDataSource.getFruits()},
        saveCallResult = {localDataSource.insertAll(it)}
    )
    /**
     * details
     */
    fun getFruitsDetailsData(id:Int) = performGetOperation(
        databaseQuery = {localDataSource.getFruit(id)},
        networkCall = {remoteDataSource.getFruitBYId(id)},
        saveCallResult = {localDataSource.insertDetails(it)}
    )
    fun getFruitAllDetail(name: String) = performGetOperation(
        databaseQuery = {localDataSource.getAllDetails(name)},
        networkCall = {remoteDataSource.getFruitByName(name)},
        saveCallResult = {localDataSource.insertAllDetails(it)}
    )

    fun getNutritionDetail(order: String) = performGetOperation(
        databaseQuery = {localDataSource.getNutritionDetail(order)},
        networkCall = {remoteDataSource.getNutritionByName(order)},
        saveCallResult = {localDataSource.insertNutritionDetails(it)}
    )
    fun getFamilyDetail(family: String)= performGetOperation(
        databaseQuery = {localDataSource.getFamilyDetail(family)},
        networkCall = {remoteDataSource.getFamilyByName(family)},
        saveCallResult = {localDataSource.insertFamilyDetail(it)}

    )
}