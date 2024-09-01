package com.samuelokello.dogdom.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.samuelokello.dogdom.R
import com.samuelokello.dogdom.data.DataSource
import com.samuelokello.dogdom.ui.home.model.HomeIcon
import com.samuelokello.dogdom.ui.shared.components.DogDomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickActions(
    homeIcons: List<HomeIcon>,
    modifier: Modifier = Modifier,
    discoverTab: Boolean = false
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                query = "",
                onQueryChange = { },
                placeholder = { Text(text = "Send the sample") },
                active = false,
                onActiveChange = { },
                onSearch = {},
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = true,
                leadingIcon = {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.LightGray
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_mic),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp),
                    )
                },
                shape = RoundedCornerShape(20.dp),
                colors = SearchBarDefaults.colors(
                    containerColor = Color.LightGray.copy(alpha = 0.1f),
                )
            ) { }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            homeIcons.forEach { icon ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = icon.icon),
                        contentDescription = icon.title,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = icon.title)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (!discoverTab) {
            StrayDogsCard(
                dogs = DataSource.takeHomeImages,
                modifier
            )
        }
    }
}

@Composable
fun StrayDogsCard(
    dogs: List<Int>,
    modifier: Modifier = Modifier
) {
    LazyRow {
        items(dogs) {
            Spacer(modifier = Modifier.width(8.dp))

            dogs.forEach { dog ->
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
                                text = "Take me Home",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = "Take care of stray dogs, please \ntake them home",
                                style = MaterialTheme.typography.bodySmall
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            DogDomButton(
                                label = "Let me",
                                containerColor = Color.Black,
                                onClick = {}
                            )

                        }
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
            }


        }

    }
}
