package com.uvg.labenclase.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.labenclase.model.Song
import com.uvg.labenclase.ui.components.SongItem
import com.uvg.labenclase.viewmodel.MusicViewModel

@Composable
fun SongListScreen(viewModel: MusicViewModel = viewModel()) {
    val songs by viewModel.allSongs.observeAsState(initial = emptyList())

    println("Canciones observadas: ${songs.size}")

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(songs) { song ->
            SongItem(song = song, onFavoriteClick = {
                viewModel.toggleFavorite(song)
            })
        }
    }
}



