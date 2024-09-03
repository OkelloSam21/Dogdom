package com.samuelokello.dogdom.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samuelokello.dogdom.data.DataSource

class HomeViewModelFactory(private val postsRepository: DataSource) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(postsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}