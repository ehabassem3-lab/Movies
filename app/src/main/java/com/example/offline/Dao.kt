package com.example.offline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.network.response.discover.MoviesResponse


@Dao
interface Dao {

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

