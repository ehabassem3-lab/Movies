package com.example.movies.data.datasource.home

import com.example.movies.mapper.toEntity
import com.example.movies.mapper.toMoviesItem
import com.example.movies.network.response.discover.MoviesResponse
import com.example.offline.Dao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: Dao
) : LocalDataSource {

    override suspend fun getMovies(
        page: Int,
        genre: Int?
    ): Result<MoviesResponse> {

        return try {

            val movies = dao.getMovies(
                page = page,
                genreId = genre ?: -1
            )

            Result.success(
                MoviesResponse(
                    page = page,
                    results = movies.map { it.toMoviesItem() }
                )
            )

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveMovies(
        page: Int,
        genre: Int?,
        response: MoviesResponse
    ): Result<Unit> {

        return try {

            val entities = response.results
                .orEmpty()
                .filterNotNull()
                .map { movie ->
                    movie.toEntity(
                        page = page,
                        genre = genre
                    )
                }

            dao.saveMovies(entities)

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
