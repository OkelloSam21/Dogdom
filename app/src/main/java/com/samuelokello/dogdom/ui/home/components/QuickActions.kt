package com.samuelokello.dogdom.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.samuelokello.dogdom.R
import com.samuelokello.dogdom.data.DataSource
import com.samuelokello.dogdom.ui.home.model.HomeIcon
import com.samuelokello.dogdom.ui.shared.components.DogdomSearchBar
import com.samuelokello.dogdom.ui.shared.components.QuickActionCard

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
            DogdomSearchBar(
                query = "",
                onQueryChange = { },
                onSearch = { },
                onActiveChange = { },
                active = false,
                enabled = true,
                content = {
                    Text(
                        text = "Search for dogs",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            )

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
            QuickActionCard(
                modifier,
                quickActionImages = DataSource.takeHomeImages,
                quickActionDescription = stringResource(R.string.take_care_of_stray_quickactionimages_please_take_them_home),
                quickActionTitle = stringResource(R.string.take_me_home),
                buttonLabel = stringResource(R.string.let_me),
                onButtonClick = { }
            )
        }
    }
}

