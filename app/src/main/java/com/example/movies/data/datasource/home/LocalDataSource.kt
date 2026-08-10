package com.example.movies.data.datasource.home

import com.example.offline.ItemEntity
import com.example.offline.MovieSectionEntity
import com.example.offline.TvSectionEntity

interface LocalDataSource {

    suspend fun saveMovieSection(
        section: MovieSectionEntity,
        movies: List<ItemEntity>
    )

    suspend fun saveTvSection(
        section: TvSectionEntity,
        tv: List<ItemEntity>
    )

    suspend fun getMovies(): List<MovieSectionEntity>

    suspend fun getTv(): List<TvSectionEntity>

    suspend fun clearMovies()

    suspend fun clearTv()
}