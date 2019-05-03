package com.cristhian.statify.objects

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

data class Song (
    @PrimaryKey @ColumnInfo(name = "id") @Expose var id: String,
    @ColumnInfo(name = "name") @Expose var name: String,
    @ColumnInfo(name = "artists") @Expose var artists: List<Artist>,

    @ColumnInfo(name = "uri") @Expose var album: Album

)