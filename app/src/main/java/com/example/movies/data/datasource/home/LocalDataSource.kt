package com.example.movies.data.datasource.home

import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesResponse

interface LocalDataSource {



        suspend fun getMovies(
            page: Int,
            genre: Int?
        ): Result<MoviesResponse>

        suspend fun saveMovies(
            page: Int,
            genre: Int?,
            response: MoviesResponse
        ): Result<Unit>


}