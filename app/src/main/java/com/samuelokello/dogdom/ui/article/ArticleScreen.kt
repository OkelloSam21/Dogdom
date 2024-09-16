import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samuelokello.dogdom.R
import com.samuelokello.dogdom.model.Comment
import com.samuelokello.dogdom.model.Post
import com.samuelokello.dogdom.ui.article.ArticleViewModel
import com.samuelokello.dogdom.ui.article.ArticleViewModelFactory
import com.samuelokello.dogdom.ui.shared.components.CustomButton
import com.samuelokello.dogdom.ui.shared.components.DogDomButton
import com.samuelokello.dogdom.ui.theme.CustomOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    modifier: Modifier = Modifier,
    postId: Int,
    viewModelFactory: ArticleViewModelFactory,
    onBackClick: () -> Unit
) {
    val viewModel: ArticleViewModel = viewModel(factory = viewModelFactory)
    val articleState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(postId) {
        viewModel.getArticleById(postId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    Row {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                painter = painterResource(R.drawable.more_1),
                                contentDescription = "More"
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        when {
            articleState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            articleState.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Error: ${articleState.error}")
                }
            }

            else -> {
                val article = articleState.article
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    ArticleContent(article)
                }
            }
        }
    }
}

@Composable
fun ArticleContent(article: Post) {
    val context = LocalContext.current
    Column (Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp, horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ConstraintLayout(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    val (image, text) = createRefs()

                    val imageRes = runCatching {
                        painterResource(id = article.author.imageRes)
                    }.getOrElse {
                        painterResource(id = R.drawable.profile)
                    }

                    Image(
                        painter = imageRes,
                        contentDescription = "Author profile picture",
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
                                start.linkTo(image.end, margin = 8.dp)
                            }
                    ) {
                        Text(text = article.author.name)
                        Text(
                            text = article.author.location ?: "Unknown Location",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.LightGray
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                DogDomButton(
                    label = "Follow",
                    onClick = { /*TODO*/ },
                )
            }
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val oneLiner = context.resources.getStringArray(article.content)

                Text(
                    text = oneLiner.first(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 1.dp, vertical = 16.dp)
                )

                val images = article.images
                if (images.size == 2) {
                    images.forEach { imageResId ->
                        val imageRes = runCatching {
                            painterResource(id = imageResId)
                        }.getOrNull()

                        if (imageRes != null) {
                            Image(
                                painter = imageRes,
                                contentDescription = "Article image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(16f / 9f)
                                    .clip(RoundedCornerShape(10.dp)),
                                contentScale = ContentScale.Crop,
                            )
                        } else {
                            Text(
                                text = "Image not available",
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(2.dp)
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = oneLiner[1],
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(horizontal = 1.dp, vertical = 16.dp)
                        )
                    }
                } else {
                    images.chunked(2).forEach { imagePair ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            imagePair.forEach { imageResId ->
                                val imageRes = runCatching {
                                    painterResource(id = imageResId)
                                }.getOrNull()

                                if (imageRes != null) {
                                    Image(
                                        painter = imageRes,
                                        contentDescription = "Article image",
                                        modifier = Modifier
                                            .weight(1f)
                                            .aspectRatio(16f / 9f)
                                            .clip(RoundedCornerShape(10.dp)),
                                        contentScale = ContentScale.Crop,
                                    )
                                } else {
                                    Text(
                                        text = "Image not available",
                                        style = MaterialTheme.typography.bodySmall,
                                        modifier = Modifier.padding(2.dp)
                                    )
                                }
                            }
                        }
                        Spacer(Modifier.height(16.dp))
                        Text(
                            text = oneLiner[2],
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(horizontal = 1.dp, vertical = 16.dp)
                        )
                    }

                    CustomButton(
                        text = "Publish My Article",
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            ArticleFooter(article)
        }

    }

}

@Composable
fun ArticleFooter (article: Post) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(text = "Message", style = MaterialTheme.typography.bodyLarge.copy(Color.Gray))
        Spacer(modifier = Modifier.height(8.dp))

        CommentSection(article.comments)

        EngagementMetrics(modifier = Modifier, article)
    }
}
@Composable
fun EngagementMetrics(
    modifier: Modifier = Modifier,
    article: Post
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.LightGray
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = "Give your Opinion",
                onValueChange = {},
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.LightGray.copy(alpha = .6f),
                    unfocusedBorderColor = Color.Transparent,
                    focusedTextColor = Color.Gray,
                    unfocusedTextColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.weight(2f))
            Row(
                modifier = Modifier.padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.like), contentDescription = "Like")
                Box {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chat),
                        contentDescription = "Comments",
                        modifier = Modifier.size(20.dp)
                    )
                    Badge(
                        content = { Text("${article.comments.size}") },
                        modifier = Modifier
//                        .size(16.dp)
                            .align(Alignment.TopEnd),
                        containerColor = CustomOrange,
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "favourite"
                )
                Icon(painter = painterResource(id = R.drawable.share), contentDescription = "share")
            }

        }
    }

}

@Composable
fun CommentSection(comments: List<Comment>) {
    LazyColumn(modifier = Modifier.height(150.dp)) {
        items(comments) { comment ->
            CommentItem(comment)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun CommentItem(comment: Comment) {
    Column {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = comment.author.imageRes),
                contentDescription = "Commenter profile picture",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))


            Column {
                Text(text = comment.author.name, fontWeight = FontWeight.Bold)
                Text(text = comment.content)
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "${comment.likes} minutes ago", color = Color.Gray)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = "Reply", color = Color.Gray)
                }
            }
            Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {
                Text(text = "${comment.likes}", color = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    painter = painterResource(id = R.drawable.like),
                    contentDescription = "Like"
                )
            }
        }
    }

}