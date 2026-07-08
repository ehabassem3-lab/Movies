package com.example.movies.mapper

//import com.example.movies.database.DataBase
//import com.example.movies.network.response.discover.MoviesItem
//
//fun MoviesItem.toEntity(genreId: Int?): DataBase {
//    return DataBase(
//        id = id ?: 0,
//        name = name.orEmpty(),
//        posterPath = posterPath,
//        backdropPath = backdropPath,
//        overview = overview.orEmpty(),
//        voteAverage = voteAverage ?: 0.0,
//        genreId = genreId ,
//        popularity = popularity?:0.0
//    )
//}
//fun List<MoviesItem>.toEntity(genreId: Int?) =
//    map { it.toEntity(genreId) }