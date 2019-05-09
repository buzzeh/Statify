package com.cristhian.statify.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.Expose


data class LibraryItem(
    @ColumnInfo(name = "track") @Expose var track: MinifiedSong
)