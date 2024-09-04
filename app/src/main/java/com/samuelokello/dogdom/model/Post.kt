package com.samuelokello.dogdom.model

data class Post(
    val id: Int = 0,
    val author: Author = Author(0, "", "",0),
    val content: String = "",
    val images: List<Int> = emptyList(),
    val likes: Int = 0,
    val comments: Int = 0,
    val shares: Int = 0
)

data class Author(
    val id: Int = 0,
    val name: String = "",
    val location: String? = "",
    val imageRes: Int = 0
)