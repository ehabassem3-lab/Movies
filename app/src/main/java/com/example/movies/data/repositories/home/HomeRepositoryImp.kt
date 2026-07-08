package com.example.movies.data.repositories.home

import com.example.movies.data.datasource.home.LocalDataSource
import com.example.movies.data.datasource.home.RemoteDataSource
import com.example.movies.domain.repositories.home.HomeRepository
import com.example.movies.network.response.details.DetailsItemResponse
import com.example.movies.network.response.details.TvDetails
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.network.response.discover.MoviesResponse
import com.example.movies.network.response.search.SearchResponse
import jakarta.inject.Inject

class HomeRepositoryImp  @Inject constructor(
    private val dataSource: RemoteDataSource ,
    private val local : LocalDataSource
) : HomeRepository {

    override suspend fun getDiscoveryTv(page: Int?, genre: Int?): Result<DiscoverResponse?> {
      val request = dataSource.getDiscover(page,genre)
        return if (request.isSuccess){
            Result.success( request.getOrNull())
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }

    override suspend fun getDiscoveryMovies(
        page: Int?,
        genre: Int?
    ): Result<MoviesResponse?> {
        val request = dataSource.getDiscoverMovies(page,genre)
        return if (request.isSuccess){
            Result.success( request.getOrNull())
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }

    override suspend fun getMovieById(id: Int): Result<DetailsItemResponse> {
        val request = dataSource.getMovieById(id)
        return if (request.isSuccess){
            Result.success( request.getOrNull()!!)
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }

    override suspend fun getTvById(id: Int): Result<TvDetails> {
        val request = dataSource.getTvById(id)
        return if (request.isSuccess){
            Result.success( request.getOrNull()!!)
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }


}