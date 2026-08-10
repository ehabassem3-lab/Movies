package com.example.offline

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(
        items: List<ItemEntity>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieSection(
        section: MovieSectionEntity
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieSectionItems(
        items: List<MovieSectionItemEntity>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvSection(
        section: TvSectionEntity
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvSectionItems(
        items: List<TvSectionItemEntity>
    )


    // =========================
    // SAVE MOVIE SECTION
    // =========================

    @Transaction
    suspend fun saveMovieSection(
        section: MovieSectionEntity,
        movies: List<ItemEntity>
    ) {
        insertMovieSection(section)

        insertItems(movies)

        val relations = movies.map { movie ->
            MovieSectionItemEntity(
                sectionId = section.id,
                movieId = movie.id
            )
        }

        insertMovieSectionItems(relations)
    }


    // =========================
    // SAVE TV SECTION
    // =========================

    @Transaction
    suspend fun saveTvSection(
        section: TvSectionEntity,
        tv: List<ItemEntity>
    ) {
        insertTvSection(section)

        insertItems(tv)

        val relations = tv.map { item ->
            TvSectionItemEntity(
                sectionId = section.id,
                tvId = item.id
            )
        }

        insertTvSectionItems(relations)
    }


    // =========================
    // GET
    // =========================

    @Query("SELECT * FROM movie_sections")
    suspend fun getMovieSections(): List<MovieSectionEntity>

    @Query("SELECT * FROM tv_sections")
    suspend fun getTvSections(): List<TvSectionEntity>


    // =========================
    // CLEAR
    // =========================

    @Query("DELETE FROM movie_sections")
    suspend fun clearMovieSections()

    @Query("DELETE FROM tv_sections")
    suspend fun clearTvSections()

    @Query("DELETE FROM movie_section_items")
    suspend fun clearMovieSectionItems()

    @Query("DELETE FROM tv_section_items")
    suspend fun clearTvSectionItems()

    @Query("DELETE FROM items WHERE type = 'movie'")
    suspend fun clearMovies()

    @Query("DELETE FROM items WHERE type = 'tv'")
    suspend fun clearTv()
}