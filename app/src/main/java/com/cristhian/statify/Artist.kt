package com.cristhian.statify

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

data class Artist(@PrimaryKey @ColumnInfo(name = "id") @Expose var id: String,
                  @ColumnInfo (name = "name") @Expose var name: String
)