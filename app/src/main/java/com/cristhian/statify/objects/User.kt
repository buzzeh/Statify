package com.cristhian.statify.objects

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*


data class User(
    @PrimaryKey @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "birthdate") var birthdate: String,
    @ColumnInfo(name = "display_name") var display_name: String,
    @ColumnInfo(name = "images") var profileImage: Image


)