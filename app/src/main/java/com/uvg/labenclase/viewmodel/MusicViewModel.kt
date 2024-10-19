package com.uvg.labenclase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uvg.labenclase.data.repository.MusicRepository
import com.uvg.labenclase.model.Artist
import com.uvg.labenclase.model.Song
import com.uvg.labenclase.model.SongWithArtist
import kotlinx.coroutines.launch

class MusicViewModel(private val repository: MusicRepository) : ViewModel() {

    val allSongs: LiveData<List<SongWithArtist>> = repository.allSongs
    val allArtists: LiveData<List<Artist>> = repository.allArtists

    fun toggleFavorite(songWithArtist: SongWithArtist) {
        viewModelScope.launch {
            val updatedSong = Song(
                id = songWithArtist.song.id,
                name = songWithArtist.song.name,
                artist = songWithArtist.song.artist,
                genre = songWithArtist.song.genre,
                color = songWithArtist.song.color,
                isFavorite = !songWithArtist.song.isFavorite
            )
            repository.updateSong(updatedSong)
        }
    }

    fun preloadDataIfEmpty() {
        viewModelScope.launch {
            repository.preloadDataIfEmpty()
        }
    }
}
