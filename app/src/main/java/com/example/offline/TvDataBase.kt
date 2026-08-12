package com.example.offline

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        TvEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TvDatabase : RoomDatabase() {

    abstract fun tvDao(): TvDao
}