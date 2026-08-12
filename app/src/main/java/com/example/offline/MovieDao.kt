package com.example.offline


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(
        movies: List<MovieEntity>
    )

    @Query("""
        SELECT * FROM movies
        WHERE page = :page
        AND genreId = :genreId
    """)
    suspend fun getMovies(
        page: Int,
        genreId: Int
    ): List<MovieEntity>
}