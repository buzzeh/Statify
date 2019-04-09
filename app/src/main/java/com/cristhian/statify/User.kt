package com.cristhian.statify

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import java.util.*


data class User(
    @PrimaryKey @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "birthdate") var birthdate: String,
    @ColumnInfo (name = "display_name") var display_name: String

)