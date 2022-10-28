package com.example.fruitapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.fruitapp.fruitlist.FruitListViewModel
import com.example.fruitapp.models.FruitsItem
import com.example.fruitapp.repository.FruitRepository
import com.example.fruitapp.utils.Resource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FruitsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: FruitListViewModel
    private val fruitList = listOf(
        FruitsItem("", "Male", 1, "", "John"),
        FruitsItem("", "Female", 2, "", "Jennie")
    )
    private val repository: FruitRepository = mockk(relaxed = true) {

        every { getFruits() } returns MutableLiveData(Resource.success(fruitList))
    }
    private val fruitsObserver: Observer<Resource<List<FruitsItem>>> = mockk(relaxed = true)

    @Before
    fun setUp() {
        viewModel = FruitListViewModel(repository)
        viewModel.repository.observeForever(fruitsObserver)
    }

    @Test
    fun `get fruits should return emit list of fruit`() {
        verify {
            fruitsObserver.onChanged(Resource.success(fruitList))
        }
        assert(viewModel.repository.value == Resource.success(fruitList))
    }
}
