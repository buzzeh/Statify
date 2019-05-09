package com.cristhian.statify.objects

import androidx.room.ColumnInfo
import com.google.gson.annotations.Expose

data class LibraryContainer(@ColumnInfo(name = "items") @Expose var items:List<LibraryItem>,
                            @ColumnInfo(name = "total") @Expose var total:Int
                    )