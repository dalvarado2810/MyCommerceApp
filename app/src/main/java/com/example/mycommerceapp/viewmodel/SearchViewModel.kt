package com.example.mycommerceapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycommerceapp.data.model.SearchModel
import com.example.mycommerceapp.data.repositories.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
): ViewModel() {

    private val _liveData = MutableLiveData<AppResource<SearchModel?>>()
    val liveData: LiveData<AppResource<SearchModel?>> = _liveData

    fun getAllSearchItems(productName: String?): LiveData<AppResource<SearchModel?>> {
        viewModelScope.launch(Dispatchers.IO) {
            _liveData.postValue(AppResource.Loading())
            _liveData.postValue(searchRepository.getAllRepositories(productName))
        }
        return liveData
    }
}