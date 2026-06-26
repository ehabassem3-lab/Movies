package com.example.movies.data.datasource.home

import com.example.movies.network.response.discover.DiscoverResponse

interface RemoteDataSource{
   suspend fun getDiscover() : Result<DiscoverResponse>
}