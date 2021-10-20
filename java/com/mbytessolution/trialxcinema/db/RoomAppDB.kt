package com.mbytessolution.trialxcinema.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1)
abstract class RoomAppDB: RoomDatabase() {
    abstract fun moviesDao(): MoviesDao?

    companion object {
        private var INSTANCE: RoomAppDB?=null
        fun getAppDatabase(context: Context): RoomAppDB? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<RoomAppDB>(
                        context.applicationContext, RoomAppDB::class.java, "MovieAppDB"
                ).allowMainThreadQueries().build();
            }

            return INSTANCE;
        }
    }

}