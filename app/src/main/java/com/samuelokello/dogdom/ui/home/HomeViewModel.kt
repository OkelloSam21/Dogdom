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

    private val likedPosts = mutableListOf<Int>()

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

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.OnCommentPressed -> {}
            is HomeAction.OnSharePressed -> {}
            is HomeAction.OnMorePressed -> {}
            is HomeAction.OnLikePressed -> {
                _state.update { currentState ->
                    val isSelectedTab = currentState.currentTab == HomeTab.Select
                    val posts = if (isSelectedTab) currentState.selectTabPosts else currentState.discoverTabPosts

                    val updatedPosts = posts.map { post ->
                        if (post.id == action.postId) {
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

                    if (isSelectedTab) {
                        currentState.copy(selectTabPosts = updatedPosts)
                    } else {
                        currentState.copy(discoverTabPosts = updatedPosts)
                    }
                }
            }
            is HomeAction.OnTabChanged -> {
                _state.update { it.copy(currentTab = action.tab) }
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