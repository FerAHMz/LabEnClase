package com.uvg.labenclase.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.uvg.labenclase.model.Song
import com.uvg.labenclase.model.SongWithArtist

@Dao
interface SongDao {

    @Transaction
    @Query("""
        SELECT * FROM songs
        ORDER BY CASE WHEN isFavorite THEN 0 ELSE 1 END, name ASC
    """)
    fun getAllSongs(): LiveData<List<SongWithArtist>>

    @Transaction
    @Query("""
        SELECT * FROM songs
        ORDER BY CASE WHEN isFavorite THEN 0 ELSE 1 END, name ASC
    """)
    suspend fun getAllSongsDirect(): List<SongWithArtist>

    @Query("SELECT COUNT(*) FROM songs")
    fun getSongCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSongs(songs: List<Song>)

    @Update
    suspend fun updateSong(song: Song)
}
