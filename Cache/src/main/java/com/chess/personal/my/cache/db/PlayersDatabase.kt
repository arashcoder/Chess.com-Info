package com.chess.personal.my.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.chess.personal.my.cache.dao.ConfigDao
import com.chess.personal.my.cache.model.CachedPlayer
import com.chess.personal.my.cache.model.Config
import javax.inject.Inject

@Database(entities = arrayOf(CachedPlayer::class,
        Config::class), version = 1)
abstract class PlayersDatabase @Inject constructor(): RoomDatabase() {

    //abstract fun cachedProjectsDao(): CachedPl

    abstract fun configDao(): ConfigDao

    companion object {

        private var INSTANCE: PlayersDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): PlayersDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                PlayersDatabase::class.java, "players.db")
                                .build()
                    }
                    return INSTANCE as PlayersDatabase
                }
            }
            return INSTANCE as PlayersDatabase
        }
    }

}