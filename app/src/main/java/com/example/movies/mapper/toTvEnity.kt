package com.example.movies.mapper

import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.MoviesItem
import com.example.offline.MovieEntity
import com.example.offline.TvEntity

fun DiscoverItem.toEntity(
    page: Int,
    genre: Int?
): TvEntity {

    return TvEntity(
        id = id ?: 0,

        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath,
        overview = overview,

        voteAverage = voteAverage,
        voteCount = voteCount,

        firstAirDate = firstAirDate,
        originalLanguage = originalLanguage,
        originalName = originalName,
        popularity = popularity,

        type = type,
        fav = fav,
        isRated = isRated,
        rate = rate,

        page = page,
        genreId = genre ?: -1
    )
}

fun TvEntity.toTvItem(): DiscoverItem {

    return DiscoverItem(
        id = id,
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath,
        overview = overview,

        voteAverage = voteAverage,
        voteCount = voteCount,

        firstAirDate = firstAirDate,
        originalLanguage = originalLanguage,
        originalName = originalName,
        popularity = popularity,

        type = type,
        fav = fav,
        isRated = isRated,
        rate = rate,
        page =  page
    )
}
