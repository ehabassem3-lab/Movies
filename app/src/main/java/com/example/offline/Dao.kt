package com.example.offline

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: ItemEntity)

    @Query("SELECT * FROM item")
    suspend fun getAllMovies(): List<ItemEntity>

    @Query("SELECT * FROM item WHERE id = :itemId")
    suspend fun getItem(itemId: Int , type : String): ItemEntity?

    @Delete
    suspend fun deleteMovie(movie: ItemEntity)

    @Query("DELETE FROM item")
    suspend fun deleteAllMovies()
}