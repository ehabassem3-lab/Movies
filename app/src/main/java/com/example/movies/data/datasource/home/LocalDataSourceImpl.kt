package com.example.movies.data.datasource.home

import com.example.movies.database.TvDao
import com.example.movies.ui.main.tabs.home.MovieSectionUiState
import com.example.movies.ui.main.tabs.home.TvSectionUiState
import com.example.offline.Dao
//import com.example.movies.database.DataBase
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: Dao
) : LocalDataSource {
    //    override suspend fun saveMovies(genreId: Int?, movies: List<DataBase>) = dao.insert(movies)
//
//
//    override suspend fun getMovies(genreId: Int?): List<DataBase>  = dao.getTvByGenre(genreId)
//
    override suspend fun getMovies(): List<MovieSectionUiState> {
        TODO("Not yet implemented")
    }

    override suspend fun getTv(): List<TvSectionUiState> {
        TODO("Not yet implemented")
    }


}