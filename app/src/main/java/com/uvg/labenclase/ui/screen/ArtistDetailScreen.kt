package com.uvg.labenclase.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.labenclase.model.Artist
import com.uvg.labenclase.model.Song
import com.uvg.labenclase.ui.components.SongItem
import com.uvg.labenclase.viewmodel.MusicViewModel

@Composable
fun ArtistDetailScreen(
    artist: Artist,
    viewModel: MusicViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val songs by viewModel.allSongs.observeAsState(initial = emptyList())

    val artistSongs = songs.filter { it.artistName == artist.name }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = artist.name, modifier = Modifier.padding(8.dp))
        Text(text = "${artist.monthlyListeners} oyentes mensuales", modifier = Modifier.padding(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(top = 16.dp)
        ) {
            items(artistSongs) { song ->
                SongItem(song = song, onFavoriteClick = {
                    viewModel.toggleFavorite(song)
                })
            }
        }
    }
}

