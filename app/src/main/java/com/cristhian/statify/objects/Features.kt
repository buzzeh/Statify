package com.cristhian.statify.objects

import androidx.room.ColumnInfo
import com.google.gson.annotations.Expose

data class Features (@ColumnInfo(name = "acousticness") @Expose var acousticness:Float?,
                     @ColumnInfo(name = "danceability") @Expose var danceability:Float?,
                     @ColumnInfo(name = "energy") @Expose var energy:Float?,
                     @ColumnInfo(name = "instrumentalness") @Expose var instrumentalness:Float?,
                     @ColumnInfo(name = "liveness") @Expose var liveness:Float?,
                     @ColumnInfo(name = "valence") @Expose var valence:Float?,
                     @ColumnInfo(name = "tempo") @Expose var tempo:Float?)