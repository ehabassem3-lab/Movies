package com.example.offline

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.network.response.cast.Cast
import com.example.movies.network.response.cast.CastItem

@Database(
    entities = [
        CastEntity::class
       ],
    version = 1,
    exportSchema = false
)
abstract class CastDataBase : RoomDatabase() {

}