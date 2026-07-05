package com.example.movies.domain.repositories.search

import com.example.movies.network.response.search.SearchResponse

interface SearchRepository {
    suspend fun searchMovies(search : String ) : Result<SearchResponse?>

}