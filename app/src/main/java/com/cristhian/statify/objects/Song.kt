package com.cristhian.statify.objects

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

data class Song (
    @PrimaryKey @ColumnInfo(name = "id") @Expose var id: String,
    @ColumnInfo(name = "name") @Expose var name: String,
    @ColumnInfo(name = "artist") @Expose var artist: String,
    @ColumnInfo(name = "album") @Expose var album:Album

)