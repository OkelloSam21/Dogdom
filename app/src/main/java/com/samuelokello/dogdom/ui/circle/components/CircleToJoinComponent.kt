package com.samuelokello.dogdom.ui.circle.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samuelokello.dogdom.R
import com.samuelokello.dogdom.ui.circle.CircleItem
import com.samuelokello.dogdom.ui.shared.components.DogDomButton

@Composable
fun CircleToJoinComponent(
    modifier: Modifier = Modifier,
    item: CircleItem,
    onClick: (id: Int) -> Unit
    ) {
    Row(
        Modifier.fillMaxWidth()
            .clickable { onClick(item.cId) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.SpaceBetween
    ) {
        Image(
            painter = painterResource(item.image),
            contentDescription = item.title,
            modifier = modifier
                .size(64.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Column {
            Text(item.title, style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold))
            Text(
                "${item.members} members",
                style = MaterialTheme.typography.labelSmall.copy(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f)
                )
            )
        }

        DogDomButton(
            label = "Join",
            onClick = { },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CircleToJoinPrev() {
    CircleToJoinComponent(
        item = CircleItem(
            cId = 0,
            title = "Samuel Okello",
            image = R.drawable.profile,
            members = 10
        ),
        onClick = {}
    )
}