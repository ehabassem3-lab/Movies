package com.example.movies.data.repositories.search

import com.example.movies.data.datasource.home.RemoteDataSource
import com.example.movies.domain.repositories.search.SearchRepository
import com.example.movies.network.response.search.SearchResponse
import jakarta.inject.Inject

class SearchRepositoryImpl  @Inject constructor(
    private val dataSource: RemoteDataSource
) : SearchRepository {
    override suspend fun searchMovies(search: String): Result<SearchResponse?> {
        val request = dataSource.searchMovies(search)
        if (request.isSuccess){
            return Result.success( request.getOrNull())
        }else{
            return Result.failure(Throwable(request.exceptionOrNull()))
        }
    }
}