package com.example.movies.data.datasource.home

import com.example.offline.Dao
import com.example.offline.ItemEntity
import com.example.offline.MovieSectionEntity
import com.example.offline.TvSectionEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: Dao
) : LocalDataSource {

    override suspend fun saveMovieSection(
        section: MovieSectionEntity,
        movies: List<ItemEntity>
    ) {
        dao.saveMovieSection(
            section = section,
            movies = movies
        )
    }

    override suspend fun saveTvSection(
        section: TvSectionEntity,
        tv: List<ItemEntity>
    ) {
        dao.saveTvSection(
            section = section,
            tv = tv
        )
    }

    override suspend fun getMovies(): List<MovieSectionEntity> {
        return dao.getMovieSections()
    }

    override suspend fun getTv(): List<TvSectionEntity> {
        return dao.getTvSections()
    }

    override suspend fun clearMovies() {
        dao.clearMovieSectionItems()
        dao.clearMovieSections()
        dao.clearMovies()
    }

    override suspend fun clearTv() {
        dao.clearTvSectionItems()
        dao.clearTvSections()
        dao.clearTv()
    }
}