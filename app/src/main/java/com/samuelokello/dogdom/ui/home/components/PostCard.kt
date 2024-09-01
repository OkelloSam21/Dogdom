package com.samuelokello.dogdom.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.samuelokello.dogdom.R
import com.samuelokello.dogdom.data.DataSource
import com.samuelokello.dogdom.model.Feature
import com.samuelokello.dogdom.model.Post
import com.samuelokello.dogdom.ui.shared.components.DogDomButton
import com.samuelokello.dogdom.ui.theme.CustomOrange


@Composable
fun PostCard(post: Post, modifier: Modifier = Modifier) {
    Column {
        Row(
            modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            ConstraintLayout {
                val (image, text) = createRefs()
                Image(
                    painter = painterResource(id = post.author.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .constrainAs(image) {
                            top.linkTo(parent.top, margin = 0.dp)
                        },
                    contentScale = ContentScale.Crop,
                )
                Text(
                    text = post.author.name,
                    Modifier
                        .padding(top = 8.dp)
                        .constrainAs(text) {
                            start.linkTo(parent.end, margin = 8.dp)
                        }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            DogDomButton(
                label = "Follow",
                onClick = { /*TODO*/ },
            )
        }
        Text(text = post.content, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(12.dp))
        ImageGrid(images = post.images)
        Spacer(modifier = Modifier.height(8.dp))
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    ReactionButtons(
                        contentDescription = "Like",
                        text = "${post.likes}",
                        onClick = { /*TODO*/ },
                        icon = R.drawable.like,
                    )
                    ReactionButtons(
                        contentDescription = "Comment",
                        text = "${post.comments}",
                        onClick = { /*TODO*/ },
                        icon = R.drawable.comments,
                    )
                    ReactionButtons(
                        contentDescription = "Share",
                        text = "${post.shares}",
                        onClick = { /*TODO*/ },
                        icon = R.drawable.share,
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "Bookmark",
                        tint = CustomOrange
                    )
                }
            }
        }
    }
}

@Composable
fun ReactionButtons(
    contentDescription: String,
    text: String,
    onClick: () -> Unit,
    icon: Int,
) {
    Row {
        Image(
            painter = painterResource(id = icon),
            contentDescription = contentDescription,

            modifier = Modifier
                .clickable { onClick()}
        )
        Spacer(Modifier.width(2.dp))
        Text(
            text = text,

            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
            modifier = Modifier
        )
    }

}

@Composable
fun ImageGrid(images: List<Int>, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(300.dp)
            .clip(RoundedCornerShape(8.dp))
    ) {
        when (images.size) {
            1 -> {
                Image(
                    painter = painterResource(id = images[0]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }

            2 -> {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    images.forEach { image ->
                        Image(
                            painter = painterResource(id = image),
                            contentDescription = null,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            3 -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Image(
                            painter = painterResource(id = images[0]),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        images.drop(1).forEach { image ->
                            Image(
                                painter = painterResource(id = image),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }

            4 -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        images.take(2).forEach { image ->
                            Image(
                                painter = painterResource(id = image),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        images.drop(2).forEach { image ->
                            Image(
                                painter = painterResource(id = image),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }
            else -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        images.take(2).forEach { image ->
                            Image(
                                painter = painterResource(id = image),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                            Image(
                                painter = painterResource(id = images[2]),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentScale = ContentScale.Crop
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                Image(
                                    painter = painterResource(id = images[3]),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Black.copy(alpha = 0.5f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "+${images.size - 3}",
                                        color = Color.White,
                                    )
                                }
                            }
                    }
                }
            }
        }
    }
}

@Composable
fun FeatureSection(
    features:List<Feature>
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(features) { feature ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(150.dp)
                    .clip(RoundedCornerShape(20.dp))
            ){
                Image(
                    painter = painterResource(id =feature.image),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.6f)),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "#" + feature.title,
                        style = MaterialTheme.typography.labelMedium.copy(color = Color.White),
                        modifier = Modifier
                    )
                }

            }
        }
    }

}