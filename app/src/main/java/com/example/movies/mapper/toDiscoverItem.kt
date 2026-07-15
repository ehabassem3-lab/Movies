package com.example.movies.mapper

import com.example.movies.network.response.FavItem
import com.example.movies.network.response.discover.DiscoverItem

fun FavItem.toDiscoverItem(): DiscoverItem {
    return DiscoverItem(
        firstAirDate = firstAirDate,
        overview = overview,
        originalLanguage = originalLanguage,
        genreIds = genreIds,
        posterPath = posterPath,
        originCountry = originCountry,
        backdropPath = backdropPath,
        originalName = originalTitle,
        popularity = popularity,
        voteAverage = voteAverage,
        name = title,
        id = id,
        voteCount = voteCount,
        type = "TV"
    )
}