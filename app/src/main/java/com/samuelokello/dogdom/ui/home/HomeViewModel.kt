package com.samuelokello.dogdom.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelokello.dogdom.data.DataSource
import com.samuelokello.dogdom.model.Feature
import com.samuelokello.dogdom.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val postsRepository: DataSource) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    var currentTab by mutableStateOf(HomeTab.Select)
        private set

    private val likedPosts = mutableListOf<Int>()

    init {
        getPosts()
        viewModelScope.launch {
            // Observe changes to currentTab and update state flow
            snapshotFlow { currentTab }
                .collect { _state.update { it.copy(currentTab = currentTab) } }
        }
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

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnCommentPressed -> {}
            is HomeAction.OnSharePressed -> {}
            is HomeAction.OnMorePressed -> {}
            // Update the `onAction` method to handle like/unlike actions correctly
            is HomeAction.OnLikePressed -> {
                _state.update { currentState ->

                    // Determine if the selected tab is active
                    val isSelectedTab = currentState.selectTabPosts.isNotEmpty()
                    // Get the posts from the selected or discover tab
                    val posts =
                        if (isSelectedTab) currentState.selectTabPosts else currentState.discoverTabPosts

                    // Map through the posts to update the likes
                    val updatedPosts = posts.map { post ->
                        if (post.id == action.postId) {
                            // Toggle the like status and update the likes count
                            val isLiked = likedPosts.contains(post.id)
                            if (isLiked) {
                                likedPosts.remove(post.id)
                                post.copy(likes = post.likes - 1)
                            } else {
                                likedPosts.add(post.id)
                                post.copy(likes = post.likes + 1)
                            }
                        } else {
                            post
                        }
                    }

                    // Update the state with the new list of posts
                    if (isSelectedTab) {
                        currentState.copy(selectTabPosts = updatedPosts)
                    } else {
                        currentState.copy(discoverTabPosts = updatedPosts)
                    }
                }
            }

            is HomeAction.OnTabChanged -> {
                currentTab = action.tab
            }
        }
    }
}

sealed interface HomeAction {
    data class OnTabChanged(val tab: HomeTab) : HomeAction
    data class OnLikePressed(val postId: Int) : HomeAction
    data object OnCommentPressed : HomeAction
    data object OnSharePressed : HomeAction
    data object OnMorePressed : HomeAction
}

data class HomeUiState(
    val currentTab: HomeTab = HomeTab.Select,
    val selectTabPosts: List<Post> = emptyList(),
    val discoverTabPosts: List<Post> = emptyList(),
    val feature: List<Feature> = emptyList()
)