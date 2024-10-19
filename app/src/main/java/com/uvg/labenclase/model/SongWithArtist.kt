package com.uvg.labenclase.model

import androidx.room.Embedded
import androidx.room.Relation

data class SongWithArtist(
    @Embedded val song: Song,
    @Relation(
        parentColumn = "artist",
        entityColumn = "id"
    )
    val artist: Artist?
) {
    val artistName: String
        get() = artist?.name ?: "Artista desconocido"

    val id: Int
        get() = song.id

    val name: String
        get() = song.name

    val genre: String
        get() = song.genre

    val color: String
        get() = song.color

    val isFavorite: Boolean
        get() = song.isFavorite
}
