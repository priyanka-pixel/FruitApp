package com.example.fruitapp.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitapp.models.FruitsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
/**
 * hilt
 * livedata: hold data and give to the view.ViewModel will observe the changes in the live data
 * coroutine
 */
@HiltViewModel
class SearchViewModel @Inject constructor(val mainRepository: MainRepository) : ViewModel() {
    val fruitLiveData = MutableLiveData<FruitsItem>()
    val errorLivedata = MutableLiveData<String>()
    fun fetchFruits(userRequest: String) {
        viewModelScope.launch {
            var response = mainRepository.getAllShowDetail(userRequest)
            if (response.isSuccessful) {
                // assign data to livedata
                fruitLiveData.postValue(response.body())
            } else {
                errorLivedata.postValue(response.errorBody().toString())
            }
        }
    }
}