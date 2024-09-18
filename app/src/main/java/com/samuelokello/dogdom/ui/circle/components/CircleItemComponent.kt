package com.samuelokello.dogdom.ui.circle.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samuelokello.dogdom.R

@Composable
fun CircleItemComponent(
    modifier: Modifier = Modifier,
    item: CircleItem
    ) {
    Column (horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(item.image),
            contentDescription = item.title,
            modifier = modifier
                .size(96.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier.height(8.dp))
        Text(item.title, style = MaterialTheme.typography.labelSmall.copy(Color.Gray))
    }


}

data class CircleItem(
    val title: String,
    val image: Int
)

@Preview(showBackground = true)
@Composable
private fun ItemPrev() {
    CircleItemComponent(
        item = CircleItem(
            title = "Samuel Okello",
            image = R.drawable.profile
        )
    )
}