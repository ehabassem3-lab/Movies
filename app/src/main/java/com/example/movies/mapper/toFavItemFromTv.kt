package com.example.movies.mapper

import com.example.movies.network.response.FavItem
import com.example.movies.network.response.discover.DiscoverItem

fun DiscoverItem.toFav(): FavItem {
    return FavItem(
        firstAirDate = firstAirDate,
        overview = overview,
        originalLanguage = originalLanguage,
        genreIds = genreIds,
        posterPath = posterPath,
        backdropPath = backdropPath,
        originalTitle = originalName,
        popularity = popularity,
        voteAverage = voteAverage,
        title = name,
        id = id,
        voteCount = voteCount,
        mediaType = "tv" ,
        fav = fav
    )
}