package com.example.movies.domain.repositories.home

import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.search.SearchResponse

interface HomeRepository {
    suspend fun getDiscoveryTv() : Result<DiscoverResponse?>

}