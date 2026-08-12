package com.example.offline

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}