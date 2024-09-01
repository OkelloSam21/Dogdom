package com.samuelokello.dogdom.ui.shared.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.samuelokello.dogdom.ui.theme.CustomOrange

@Composable
fun DogDomButton(
    label: String,
    containerColor:Color? = CustomOrange,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor ?: CustomOrange,
        ),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier.height(32.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodySmall)
    }
}