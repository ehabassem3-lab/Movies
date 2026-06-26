package com.example.movies.domain.repositories.home

import com.example.movies.network.response.discover.DiscoverResponse

interface HomeRepository {
    suspend fun getDiscoveryTv() : Result<DiscoverResponse?>
}