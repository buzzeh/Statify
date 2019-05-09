package com.cristhian.statify.database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cristhian.statify.objects.LibraryItem
import com.cristhian.statify.objects.MinifiedSong
import com.cristhian.statify.objects.Song

@Dao
interface SongDao{

    @Query("SELECT * FROM library_table ORDER BY name")
    fun getLibrary(): LiveData<List<MinifiedSong>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addSong(song: MinifiedSong)

    @Query("DELETE FROM library_table")
    fun deleteAll()

}