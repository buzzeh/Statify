package com.cristhian.statify.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "song_table")
data class Song (
    @PrimaryKey @ColumnInfo(name = "id") @Expose var id: String,
    @ColumnInfo(name = "name") @Expose var name: String,
    @ColumnInfo(name = "artists") @Expose var artists: List<Artist>,
    @ColumnInfo(name = "albums") @Expose var album: Album,
    @ColumnInfo(name = "duration") @Expose var duration_ms: Integer,
    @ColumnInfo(name = "explicit") @Expose var explicit: Boolean,
    @ColumnInfo(name = "uri") @Expose var uri: String,
    @ColumnInfo(name = "popularity") @Expose var popularity:Integer


)