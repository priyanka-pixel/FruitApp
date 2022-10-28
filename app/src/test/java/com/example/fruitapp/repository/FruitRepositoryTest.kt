package com.example.fruitapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import androidx.lifecycle.Observer
import com.example.fruitapp.local.FruitsDAO
import com.example.fruitapp.models.FruitsItem
import com.example.fruitapp.remote.FruitRemoteDataSource
import com.example.fruitapp.utils.Resource
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FruitRepositoryTest {
    //Rule
//AAA pattern
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    //Arrange

    private val fruitsObserver: Observer<Resource<List<FruitsItem>>> = mockk(relaxed = true)
    private val fruitsObserverDetail:Observer<Resource<FruitsItem>> = mockk(relaxed = true)


    private val remoteDataSource: FruitRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: FruitsDAO = mockk(relaxed = true)
    private lateinit var repository: FruitRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp(){
        repository = FruitRepository(remoteDataSource, localDataSource)
        //coroutines
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }
    @Test
    fun `get fruit should return livedata of fruits API Data`(){
        repository.getFruits().observeForever(fruitsObserver)
        //Assert: true, false, equals
        //verify: times; method called
        verify{ fruitsObserver.onChanged(any())}
    }
    //unit test
    //re.fun(3)
    //other var with list
    //safe code
    @Test
    fun `get fruit detail should return livedata of single fruit data`(){
        repository.getFruits().observeForever(fruitsObserver)
        verify {fruitsObserver.onChanged(any()) }
    }
}






