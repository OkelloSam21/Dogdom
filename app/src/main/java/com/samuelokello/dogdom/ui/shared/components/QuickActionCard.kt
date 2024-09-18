package com.samuelokello.dogdom.ui.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun QuickActionCard(
    modifier: Modifier = Modifier,
    quickActionImages: List<Int>,
    quickActionTitle: String,
    quickActionDescription: String,
    buttonLabel: String,
    onButtonClick: () -> Unit
) {
    LazyRow {
        items(quickActionImages) {
            Spacer(modifier = modifier.width(8.dp))

            quickActionImages.forEach { dog ->
                Card(
                    modifier = Modifier.size(339.dp, 220.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = dog),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = quickActionTitle,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = quickActionDescription,
//                                ,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            DogDomButton(
                                label = buttonLabel,
                                containerColor = Color.Black,
                                onClick = onButtonClick
                            )

                        }
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        }

    }
}