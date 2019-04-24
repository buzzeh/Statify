package com.cristhian.statify.objects

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

data class Image(//@ColumnInfo(name = "height") @Expose var height: Int,

                 @ColumnInfo(name = "url") @Expose var url: String

                 //@ColumnInfo(name = "width") @Expose var width:Int
)