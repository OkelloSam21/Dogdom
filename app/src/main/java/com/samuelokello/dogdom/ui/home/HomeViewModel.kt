package com.samuelokello.dogdom.ui.home

import androidx.lifecycle.ViewModel
import com.samuelokello.dogdom.data.DataSource
import com.samuelokello.dogdom.model.Feature
import com.samuelokello.dogdom.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(private val postsRepository: DataSource) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    init {
        getPosts()
    }

    private fun getPosts() {
        _state.update {
            it.copy(
                selectTabPosts = postsRepository.posts,
                discoverTabPosts = postsRepository.discoverPosts,
                feature = postsRepository.feature
            )
        }
    }

}

data class HomeUiState(
    val selectTabPosts: List<Post> = emptyList(),
    val discoverTabPosts: List<Post> = emptyList(),
    val feature: List<Feature> = emptyList()
)