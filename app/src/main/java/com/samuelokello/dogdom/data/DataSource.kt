package com.samuelokello.dogdom.data

import com.samuelokello.dogdom.R
import com.samuelokello.dogdom.model.Author
import com.samuelokello.dogdom.model.Comment
import com.samuelokello.dogdom.model.Feature
import com.samuelokello.dogdom.model.Post
import com.samuelokello.dogdom.ui.circle.CircleItem

object DataSource {
    private val comments = listOf(
        Comment(
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = "This is a comment",
            likes = 10,
            timeStamp = 5
        ),
        Comment(
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = "This is a comment",
            likes = 10,
            timeStamp = 5
        ),
        Comment(
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = "This is a comment",
            likes = 10,
            timeStamp = 5
        ),
        Comment(
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = "This is a comment",
            likes = 10,
            timeStamp = 5
        ),
        Comment(
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = "This is a comment",
            likes = 10,
            timeStamp = 5
        )
    )

    var posts = listOf(
        Post(
            id = 1,
            author = Author(
                id = 1,
                name = "Mirable Swift",
                imageRes = R.drawable.mirable_profile,
                location = "Nairobi Kenya"
            ),
            content = R.array.lorem_ipsum,
            images = listOf(
                R.drawable.mirable_swift,
            ).shuffled(),
            likes = 100,
            comments = comments,
            shares = 5
        ),
        Post(
            id = 2,
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = R.array.lorem_ipsum,
            images = listOf(
                R.drawable.dog,
                R.drawable.dog2,
                R.drawable.dog3,
                R.drawable.dog4
            ).shuffled(),
            likes = 100,
            comments = comments,
            shares = 5
        ),
        Post(
            id = 3,
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = R.array.lorem_ipsum,
            images = listOf(
                R.drawable.dog3,
                R.drawable.dog4,
                R.drawable.dog5
            ).shuffled(),
            likes = 100,
            comments = comments,
            shares = 5
        ),
        Post(
            id = 4,
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = R.array.lorem_ipsum,
            images = listOf(
                R.drawable.dog4,
                R.drawable.dog5,
                R.drawable.dog
            ).shuffled(),
            likes = 100,
            comments = comments,
            shares = 5
        ),
        Post(
            id = 5,
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = R.array.lorem_ipsum,
            images = listOf(
                R.drawable.dog5,
                R.drawable.dog,
                R.drawable.dog2
            ).shuffled(),
            likes = 100,
            comments = comments,
            shares = 5
        )
    )

    var discoverPosts = listOf(
        Post(
            id = 1,
            author = Author(
                id = 1,
                name = "Huntington",
                location = "Golden Retriever Mobile",
                imageRes = R.drawable.hutington
            ),
            content = R.array.lorem_ipsum,
            images = listOf(
                R.drawable.hutington_1,
                R.drawable.hutington_2,
                R.drawable.hutington_3,
                R.drawable.hutington_4,
                R.drawable.hutington_5,
                R.drawable.hutington_6,
            ).shuffled(),
            likes = 100,
            comments = comments,
            shares = 5
        ),
        Post(
            id = 2,
            author = Author(
                id = 1,
                name = "Quentin Raman",
                imageRes = R.drawable.profile
            ),
            content =R.array.lorem_ipsum,
            images = listOf(
               R.drawable.hutington_2
            ),
            likes = 100,
            comments = comments,
            shares = 5
        ),
        Post(
            id = 3,
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = R.array.lorem_ipsum,
            images = listOf(
                R.drawable.dog3,
                R.drawable.dog4,
                R.drawable.dog5
            ).shuffled(),
            likes = 1668,
            comments = comments,
            shares = 5233
        ),
        Post(
            id = 4,
            author = Author(
                id = 1,
                name = "Edgar",
                imageRes = R.drawable.profile
            ),
            content = R.array.lorem_ipsum,
            images = listOf(
                R.drawable.dog4,
                R.drawable.dog5,
                R.drawable.dog,
                R.drawable.dog4,
                R.drawable.dog5,
                R.drawable.dog
            ).shuffled(),
            likes = 100,
            comments = comments,
            shares = 5
        ),
        Post(
            id = 5,
            author = Author(
                id = 1,
                name = "Alexandra",
                location = "Labrador Peninsular Atlanta",
                imageRes = R.drawable.profile
            ),
            content = R.array.lorem_ipsum,
            images = listOf(
                R.drawable.dog_out
            ).shuffled(),
            likes = 100,
            comments = comments,
            shares = 5
        )
    )

    val takeHomeImages = listOf(
        R.drawable.shuffling,
        R.drawable.shufle2
    )

    val feature = listOf(
        Feature(
            image = R.drawable.feature_1,
            title = "Feature 1"
        ),
        Feature(
            image = R.drawable.hotspot,
            title = "Feature 2"
        ),
    )

    fun getPostById(postId: Int): Post {
        return posts.find { it.id == postId } ?: Post()
    }

    val circleItems = listOf(
        CircleItem(
            cId = 1,
            title = "Golden Retriever",
            image = R.drawable.dog,
            members = 10
        ),
        CircleItem(
            cId = 2,
            title = "Back of the head",
            image = R.drawable.dog2,
            members = 342
        ),
        CircleItem(
            cId = 3,
            title = "Adopt",
            image = R.drawable.dog3,
            members = 156
        ),
        CircleItem(
            cId = 4,
            title = "Labrador Pet",
            image = R.drawable.dog4,
            members = 96
        ),
    )
}