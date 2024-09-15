package com.samuelokello.dogdom.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.samuelokello.dogdom.R
import com.samuelokello.dogdom.model.Feature
import com.samuelokello.dogdom.model.Post
import com.samuelokello.dogdom.ui.home.HomeAction
import com.samuelokello.dogdom.ui.shared.components.DogDomButton
import com.samuelokello.dogdom.ui.shared.components.ImageGrid
import com.samuelokello.dogdom.ui.theme.CustomOrange


@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    post: Post,
    event: (HomeAction) -> Unit,
    selectTab: Boolean = true,
    onPostClick: (postId: Int) -> Unit
    ) {
    val context = LocalContext.current
    Column (
        modifier = Modifier.clickable (
            onClick = { onPostClick(post.id) }
        )
    ){
        Row(
            modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
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
                Column(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .constrainAs(text) {
                            start.linkTo(parent.end, margin = 8.dp)
                        }
                ) {
                    Text(
                        text = post.author.name,
                    )
                    if (!selectTab) {
                        Text(
                            text = post.author.location ?: "Nairobi Kenya",
                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray, fontWeight = FontWeight.Medium),
                        )
                    }
                }

            }
            Spacer(modifier = Modifier.weight(1f))
            DogDomButton(
                label = "Follow",
                onClick = { /*TODO*/ },
            )
        }
        val text = context.resources.getStringArray(post.content)
        Text(text = text.first() , style = MaterialTheme.typography.bodySmall)
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
                        onClick = { event(HomeAction.OnLikePressed(post.id)) },
                        icon = R.drawable.like,
                    )
                    ReactionButtons(
                        contentDescription = "Comment",
                        text = "${post.comments.size}",
                        onClick = { event(HomeAction.OnCommentPressed) },
                        icon = R.drawable.comments,
                    )
                    ReactionButtons(
                        contentDescription = "Share",
                        text = "${post.shares}",
                        onClick = { event(HomeAction.OnSharePressed) },
                        icon = R.drawable.share,
                    )
                }
                IconButton(
                    onClick = {event(HomeAction.OnMorePressed)},
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = "More",
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
