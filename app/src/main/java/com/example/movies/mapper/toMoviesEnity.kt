package com.example.movies.mapper

import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.network.response.discover.MoviesResponse
import com.example.offline.MovieEntity

fun MoviesItem.toEntity(
    page: Int,
    genre: Int?
): MovieEntity {

    return MovieEntity(
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

fun MovieEntity.toMoviesItem(): MoviesItem {

    return MoviesItem(
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
