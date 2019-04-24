package com.cristhian.statify.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cristhian.statify.objects.Song


@Database(entities = [Song::class], version = 1, exportSchema = false)
abstract class SongRoomDatabase: RoomDatabase(){
    abstract fun songDao(): SongDao

    companion object {
        @Volatile
        private var INSTANCE: SongRoomDatabase? = null

        fun getDatabase(
            context: Context
        ): SongRoomDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SongRoomDatabase::class.java,
                    "Song_library"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}