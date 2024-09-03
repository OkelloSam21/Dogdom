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
                name = "Huntington",
                location = "Golden Retriever Mobile",
                imageRes = R.drawable.hutington
            ),
            content = "With golden retriever together of the day is always short, \n soon to the New Year, leave you in the city",
            images = listOf(
                R.drawable.hutington_1,
                R.drawable.hutington_2,
                R.drawable.hutington_3,
                R.drawable.hutington_4,
                R.drawable.hutington_5,
                R.drawable.hutington_6,
            ).shuffled(),
            likes = 100,
            comments = 10,
            shares = 5
        ),
        Post(
            id = 2,
            author = Author(
                id = 1,
                name = "Quentin Raman",
                imageRes = R.drawable.profile
            ),
            content = "Your dog is only a part of your world, but to your dog, \nyou are the world",
            images = listOf(
               R.drawable.hutington_2
            ),
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
            likes = 1668,
            comments = 658,
            shares = 5233
        ),
        Post(
            id = 4,
            author = Author(
                id = 1,
                name = "Edgar",
                imageRes = R.drawable.profile
            ),
            content = "This is a post about dogs",
            images = listOf(
                R.drawable.dog4,
                R.drawable.dog5,
                R.drawable.dog,
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
                name = "Alexandra",
                location = "Labrador Peninsular Atlanta",
                imageRes = R.drawable.profile
            ),
            content = "Take your dog out and play.",
            images = listOf(
                R.drawable.dog_out
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