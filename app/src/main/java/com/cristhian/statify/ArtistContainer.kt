package com.cristhian.statify

import androidx.room.ColumnInfo

data class ArtistContainer (@ColumnInfo (name = "items") var items:List<Artist>)