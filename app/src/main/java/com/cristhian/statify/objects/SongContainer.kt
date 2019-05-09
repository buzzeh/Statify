package com.cristhian.statify.objects

import androidx.room.ColumnInfo
import com.cristhian.statify.objects.Song
import com.google.gson.annotations.Expose

data class SongContainer (@ColumnInfo(name = "items") @Expose var items:List<Song>)