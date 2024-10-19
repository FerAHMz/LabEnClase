package com.uvg.labenclase.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.uvg.labenclase.ui.screen.ArtistDetailScreen
import com.uvg.labenclase.ui.screen.ArtistListScreen
import com.uvg.labenclase.ui.screen.BottomNavigationBar
import com.uvg.labenclase.ui.screen.NavigationItem
import com.uvg.labenclase.ui.screen.SongListScreen
import com.uvg.labenclase.viewmodel.MusicViewModel

@Composable
fun MelodyMasterApp(viewModel: MusicViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavigationItem.Explore.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavigationItem.Explore.route) {
                SongListScreen(viewModel = viewModel)
            }

            composable(NavigationItem.Artists.route) {
                ArtistListScreen(viewModel = viewModel, navController = navController)
            }

            composable("artistDetail/{artistName}") { backStackEntry ->
                val artistName = backStackEntry.arguments?.getString("artistName")
                val artist = viewModel.allArtists.value?.find { it.name == artistName }

                artist?.let {
                    ArtistDetailScreen(artist = it, viewModel = viewModel, onBackClick = {
                        navController.popBackStack()
                    })
                }
            }
        }
    }
}

