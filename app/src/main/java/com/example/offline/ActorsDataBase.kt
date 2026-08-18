package com.example.offline

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies.network.response.cast.Actor
import com.example.movies.network.response.cast.CastItem

@Database(
    entities = [
        ActorEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ActorsDataBase : RoomDatabase(){

}