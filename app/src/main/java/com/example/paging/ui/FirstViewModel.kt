package com.example.paging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.paging.repository.Paging
import com.example.paging.network.ObjectRickAndMortyApi

class FirstViewModel(private val apiService:ObjectRickAndMortyApi) : ViewModel() {
    val listData = Pager(PagingConfig(pageSize = 10)) {
        Paging(apiService)
    }.flow.cachedIn(viewModelScope)
}
