package com.example.offline

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.ui.main.tabs.home.MovieSectionUiState

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: ItemEntity)

    @Query("SELECT * FROM item")
    suspend fun getAllMovies(): List<MovieSectionUiState>

    @Query("SELECT * FROM item WHERE id = :itemId AND type  = :type" )
    suspend fun getItem(itemId: Int , type : String): ItemEntity?

    @Delete
    suspend fun deleteMovie(movie: ItemEntity)

    @Query("DELETE FROM item")
    suspend fun deleteAllMovies()
}