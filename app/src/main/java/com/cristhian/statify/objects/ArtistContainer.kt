package com.cristhian.statify.objects

import androidx.room.ColumnInfo
import com.cristhian.statify.objects.Artist

data class ArtistContainer (@ColumnInfo (name = "items") var items:List<Artist>)