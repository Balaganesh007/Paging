package com.example.paging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.paging.network.ObjectRickAndMortyApi


class FirstViewModelFactory(private val apiService: ObjectRickAndMortyApi):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstViewModel::class.java)) {
            return FirstViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}