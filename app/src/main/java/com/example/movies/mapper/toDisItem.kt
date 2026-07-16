package com.example.movies.mapper

import com.example.movies.network.response.FavItem
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.MoviesItem

fun MoviesItem.toDiscoverItem(): DiscoverItem {
    return DiscoverItem(
        firstAirDate = firstAirDate,
        overview = overview,
        originalLanguage = originalLanguage,
        genreIds = genreIds,
        posterPath = posterPath,
        originCountry = originCountry,
        backdropPath = backdropPath,
        originalName = originalName,
        popularity = popularity,
        voteAverage = voteAverage,
        name = name,
        id = id,
        voteCount = voteCount,
        type = "movie"
    )
}