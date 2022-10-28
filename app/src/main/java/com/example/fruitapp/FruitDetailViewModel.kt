package com.example.fruitapp

import androidx.lifecycle.*
import com.example.fruitapp.models.FruitsItem
import com.example.fruitapp.repository.FruitRepository
import com.example.fruitapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FruitDetailViewModel @Inject constructor(
    private val repository: FruitRepository
) : ViewModel() {
    private val _id = MutableLiveData<Int>()
    private val _fruit = _id.switchMap { id ->
        repository.getFruitsDetailsData(id)
    }
    val fruit: LiveData<Resource<FruitsItem>> = _fruit
    fun startDetailsCall(id: Int) {
        _id.value = id
    }

    private val _name = MutableLiveData<String>()
    private val _fruitname = _name.switchMap { name ->
        repository.getFruitAllDetail(name)
    }
    val fruitname: LiveData<Resource<FruitsItem>> = _fruitname
    fun startFruitDetailcall(name: String) {
        _name.value = name
    }

    private val _order = MutableLiveData<String>()
    private val _fruitorder = _order.switchMap { order ->
        repository.getNutritionDetail(order)
    }
    val fruitorder: LiveData<Resource<FruitsItem>> = _fruitorder
    fun startorderDetailcall(order: String) {
        _order.value = order
    }

    private val _family = MutableLiveData<String>()
    private val _fruitfamily = _family.switchMap { family ->
        repository.getFamilyDetail(family)
    }
    val fruitfamily: LiveData<Resource<FruitsItem>> = _fruitfamily
    fun startFamilyDetailcall(family: String) {
        _family.value = family
    }
}