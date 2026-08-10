package com.example.offline

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        ItemEntity::class,
        MovieSectionEntity::class,
        TvSectionEntity::class,
        MovieSectionItemEntity::class,
        TvSectionItemEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): Dao
}