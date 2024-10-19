package com.uvg.labenclase.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.uvg.labenclase.model.Artist

@Composable
fun ArtistItem(artist: Artist, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column {
            Text(text = artist.name, fontWeight = FontWeight.Bold)
            Text(text = "${artist.monthlyListeners} oyentes mensuales")
        }
    }
}
