package com.example.movies.data.datasource.home

import com.example.movies.ui.main.tabs.home.MovieSectionUiState
import com.example.movies.ui.main.tabs.home.TvSectionUiState


interface LocalDataSource {
//    suspend fun saveMovies( genreId: Int? , movies: List<DataBase>)
//    suspend fun getMovies (genreId: Int?) :  List<DataBase>

      suspend fun getMovies () : List<MovieSectionUiState>
    suspend fun getTv () : List<TvSectionUiState>


}