package com.samuelokello.dogdom.model

import androidx.annotation.ArrayRes

data class Post(
    val id: Int = 0,
    val author: Author = Author(0, "", "",0),
    @ArrayRes val content: Int = 0,
    val images: List<Int> = emptyList(),
    val likes: Int = 0,
    val comments: List<Comment> = emptyList(),
    val shares: Int = 0
)

data class Author(
    val id: Int = 0,
    val name: String = "",
    val location: String? = "",
    val imageRes: Int = 0
)

data class Comment(
    val author: Author,
    val content: String,
    val likes: Int,
    val timeStamp: Int
)