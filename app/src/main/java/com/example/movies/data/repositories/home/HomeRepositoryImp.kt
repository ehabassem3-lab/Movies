package com.example.movies.data.repositories.home

import com.example.movies.data.datasource.home.RemoteDataSource
import com.example.movies.domain.repositories.home.HomeRepository
import com.example.movies.network.response.discover.DiscoverResponse
import jakarta.inject.Inject

class HomeRepositoryImp  @Inject constructor(
    private val dataSource: RemoteDataSource
) : HomeRepository {

    override suspend fun getDiscoveryTv(): Result<DiscoverResponse?> {
      val request = dataSource.getDiscover()
        if (request.isSuccess){
            return Result.success( request.getOrNull())
        }else{
            return Result.failure(Throwable(request.exceptionOrNull()))
        }
    }
}