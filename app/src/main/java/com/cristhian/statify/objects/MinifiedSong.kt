package com.cristhian.statify.objects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity (tableName = "library_table")
data class MinifiedSong (
    @PrimaryKey @ColumnInfo(name = "id") @Expose var id:String,
    @ColumnInfo(name = "uri") @Expose var uri:String,
    @ColumnInfo(name = "name") @Expose var name:String,
    @ColumnInfo(name = "explicit") @Expose var explicit:Boolean,
    @ColumnInfo(name = "duration_ms") @Expose var duration_ms:Int,
    @ColumnInfo(name = "key") @Expose var key:Int?,
    @ColumnInfo(name = "mode") @Expose var mode:Int?,
    @ColumnInfo(name = "time_signature") @Expose var time_signature:Int?,
    @ColumnInfo(name = "acousticness") @Expose var acousticness:Float?,
    @ColumnInfo(name = "danceability") @Expose var danceability:Float?,
    @ColumnInfo(name = "energy") @Expose var energy:Float?,
    @ColumnInfo(name = "instrumentalness") @Expose var instrumentalness:Float?,
    @ColumnInfo(name = "liveness") @Expose var liveness:Float?,
    @ColumnInfo(name = "valence") @Expose var valence:Float?,
    @ColumnInfo(name = "tempo") @Expose var tempo:Float?

    )