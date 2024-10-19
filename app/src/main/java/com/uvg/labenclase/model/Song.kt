package com.uvg.labenclase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Song(
    @PrimaryKey val id: Int,
    val name: String,
    val artist: String,
    val genre: String,
    val color: String,
    val isFavorite: Boolean
)
