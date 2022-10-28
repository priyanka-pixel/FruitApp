package com.example.fruitapp.fruitlist


import androidx.lifecycle.ViewModel
import com.example.fruitapp.repository.FruitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FruitListViewModel @Inject constructor(
    repository: FruitRepository
) : ViewModel() { val repository = repository.getFruits() }