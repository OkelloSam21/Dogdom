package com.samuelokello.dogdom.data

import com.samuelokello.dogdom.R
import com.samuelokello.dogdom.model.Author
import com.samuelokello.dogdom.model.Feature
import com.samuelokello.dogdom.model.Post

object DataSource {
    var posts = listOf(
        Post(
            id = 1,
            author = Author(
                id = 1,
                name = "Mirable Swift",
                imageRes = R.drawable.mirable_profile
            ),
            content = "This is a post about dogs",
            images = listOf(
                R.drawable.mirable_swift,
            ).shuffled(),
            likes = 100,
            comments = 10,
            shares = 5
        ),
        Post(
            id = 2,
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = "This is a post about dogs",
            images = listOf(
                R.drawable.dog,
                R.drawable.dog2,
                R.drawable.dog3,
                R.drawable.dog4
            ).shuffled(),
            likes = 100,
            comments = 10,
            shares = 5
        ),
        Post(
            id = 3,
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = "This is a post about dogs",
            images = listOf(
                R.drawable.dog3,
                R.drawable.dog4,
                R.drawable.dog5
            ).shuffled(),
            likes = 100,
            comments = 10,
            shares = 5
        ),
        Post(
            id = 4,
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = "This is a post about dogs",
            images = listOf(
                R.drawable.dog4,
                R.drawable.dog5,
                R.drawable.dog
            ).shuffled(),
            likes = 100,
            comments = 10,
            shares = 5
        ),
        Post(
            id = 5,
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = "This is a post about dogs",
            images = listOf(
                R.drawable.dog5,
                R.drawable.dog,
                R.drawable.dog2
            ).shuffled(),
            likes = 100,
            comments = 10,
            shares = 5
        )
    )

    var discoverPosts = listOf(
        Post(
            id = 1,
            author = Author(
                id = 1,
                name = "Mirable Swift",
                imageRes = R.drawable.mirable_profile
            ),
            content = "This is a post about dogs",
            images = listOf(
                R.drawable.mirable_swift,
            ).shuffled(),
            likes = 100,
            comments = 10,
            shares = 5
        ),
        Post(
            id = 2,
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = "This is a post about dogs",
            images = listOf(
                R.drawable.dog,
                R.drawable.dog2,
                R.drawable.dog3,
                R.drawable.dog4
            ).shuffled(),
            likes = 100,
            comments = 10,
            shares = 5
        ),
        Post(
            id = 3,
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = "This is a post about dogs",
            images = listOf(
                R.drawable.dog3,
                R.drawable.dog4,
                R.drawable.dog5
            ).shuffled(),
            likes = 100,
            comments = 10,
            shares = 5
        ),
        Post(
            id = 4,
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = "This is a post about dogs",
            images = listOf(
                R.drawable.dog4,
                R.drawable.dog5,
                R.drawable.dog
            ).shuffled(),
            likes = 100,
            comments = 10,
            shares = 5
        ),
        Post(
            id = 5,
            author = Author(
                id = 1,
                name = "Samuel Okello",
                imageRes = R.drawable.profile
            ),
            content = "This is a post about dogs",
            images = listOf(
                R.drawable.dog5,
                R.drawable.dog,
                R.drawable.dog2
            ).shuffled(),
            likes = 100,
            comments = 10,
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
}