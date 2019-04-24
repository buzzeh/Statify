package com.cristhian.statify.objects

import androidx.room.ColumnInfo
import com.cristhian.statify.objects.Image

data class ImageContainer(@ColumnInfo(name = "images") var images:List<Image>)