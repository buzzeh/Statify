package com.cristhian.statify.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.cristhian.statify.objects.MinifiedSong
import com.cristhian.statify.objects.Song


class SongRepository(private val songDao:SongDao) {

    val library: LiveData<List<MinifiedSong>> = songDao.getLibrary()

    @WorkerThread
    fun insert(song:MinifiedSong) {
        songDao.addSong(song)
    }

    @WorkerThread
    fun deleteAll() {
        songDao.deleteAll()
    }

}
