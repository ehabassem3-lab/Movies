package com.example.movies.data.datasource.home

import com.example.movies.database.TvDao
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


}