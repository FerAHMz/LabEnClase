package com.uvg.labenclase.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uvg.labenclase.model.Artist

@Dao
interface ArtistDao {

    @Query("SELECT * FROM artists")
    fun getAllArtists(): LiveData<List<Artist>>

    @Query("SELECT * FROM artists")
    suspend fun getAllArtistsDirect(): List<Artist>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtists(artists: List<Artist>)
}


