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
import androidx.navigation.NavController
import com.uvg.labenclase.model.Artist
import com.uvg.labenclase.ui.components.ArtistItem
import com.uvg.labenclase.viewmodel.MusicViewModel

@Composable
fun ArtistListScreen(viewModel: MusicViewModel = viewModel(), navController: NavController) {
    val artists by viewModel.allArtists.observeAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(artists) { artist ->
            ArtistItem(artist = artist, onClick = {
                navController.navigate("artistDetail/${artist.name}")
            })
        }
    }
}

