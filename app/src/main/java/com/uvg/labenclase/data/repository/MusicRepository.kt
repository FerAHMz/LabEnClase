package com.uvg.labenclase.data.repository

import androidx.lifecycle.LiveData
import com.uvg.labenclase.data.db.ArtistDao
import com.uvg.labenclase.data.db.SongDao
import com.uvg.labenclase.data.fake.DataGenerator
import com.uvg.labenclase.model.Artist
import com.uvg.labenclase.model.Song
import com.uvg.labenclase.model.SongWithArtist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MusicRepository(private val songDao: SongDao, private val artistDao: ArtistDao) {

    val allSongs: LiveData<List<SongWithArtist>> = songDao.getAllSongs()
    val allArtists: LiveData<List<Artist>> = artistDao.getAllArtists()

    suspend fun insertSongs(songs: List<Song>) {
        withContext(Dispatchers.IO) {
            songDao.insertSongs(songs)
        }
    }

    suspend fun insertArtists(artists: List<Artist>) {
        withContext(Dispatchers.IO) {
            artistDao.insertArtists(artists)
        }
    }

    suspend fun updateSong(song: Song) {
        withContext(Dispatchers.IO) {
            songDao.updateSong(song)
        }
    }

    suspend fun preloadDataIfEmpty() {
        withContext(Dispatchers.IO) {
            if (songDao.getAllSongsDirect().isEmpty() && artistDao.getAllArtistsDirect().isEmpty()) {

                val songs = DataGenerator.getSongs().map { songDTO ->
                    Song(
                        id = songDTO.id,
                        name = songDTO.name,
                        artist = songDTO.artist_id,
                        genre = songDTO.genre,
                        color = generateRandomColor(),
                        isFavorite = false
                    )
                }

                val artists = DataGenerator.getArtists().map { artistDTO ->
                    Artist(
                        id = artistDTO.id,
                        name = artistDTO.name,
                        monthlyListeners = artistDTO.monthlyListeners,
                        albumCount = artistDTO.album_count
                    )
                }

                insertSongs(songs)
                insertArtists(artists)
            }
        }
    }

    private fun generateRandomColor(): String {
        val random = java.util.Random()
        return String.format("#%06x", random.nextInt(0xFFFFFF + 1))
    }
}
