package com.samuelokello.dogdom.ui.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samuelokello.dogdom.data.DataSource
import com.samuelokello.dogdom.model.Post
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {
    private val _state = MutableStateFlow(ArticleUiState())
    val state = _state.asStateFlow()

    fun getArticleById(id: Int) {
        viewModelScope.launch {
            _state.value = ArticleUiState(isLoading = true)
            try {
                val post = DataSource.getPostById(id)
                _state.value = ArticleUiState(isLoading = false, article = post)
            } catch (e: Exception) {
                _state.value = ArticleUiState(isLoading = false, error = e.message)
            }
        }
    }
}

data class ArticleUiState(
    val isLoading: Boolean = true,
    val article: Post = Post(),
    val error: String? = null
)