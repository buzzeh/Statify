package com.cristhian.statify.objects

import androidx.room.ColumnInfo
import com.cristhian.statify.objects.Song

data class SongContainer (@ColumnInfo(name = "items") var items:List<Song>)