package com.example.movies.data.datasource.home

import com.example.movies.mapper.toEntity
import com.example.movies.mapper.toMoviesItem
import com.example.movies.mapper.toTvItem
import com.example.movies.network.response.discover.DiscoverResponse
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.network.response.discover.MoviesResponse
import com.example.offline.MovieDao
import com.example.offline.TvDao
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val TvDao: TvDao ,
    private val MovieDao : MovieDao
) : LocalDataSource {

    override suspend fun getMovies(
        page: Int,
        genre: Int?
    ): Result<MoviesResponse> {

        return try {

            val movies = MovieDao.getMovies(
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

            MovieDao.saveMovies(entities)

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getTv(
        page: Int,
        genre: Int?
    ): Result<DiscoverResponse> {
        return try {

            val tv = TvDao.getTv(
                page = page,
                genreId = genre ?: -1
            )

            Result.success(
                DiscoverResponse(
                    page = page,
                    results = tv.map { it.toTvItem() }
                )
            )

        } catch (e: Exception) {
            Result.failure(e)
        }    }

    override suspend fun saveTv(
        page: Int,
        genre: Int?,
        response: DiscoverResponse
    ): Result<Unit> {
        return try {

            val entities = response.results
                .orEmpty()
                .filterNotNull()
                .map { tv ->
                    tv.toEntity(
                        page = page,
                        genre = genre
                    )
                }

            TvDao.saveTv(entities)

            Result.success(Unit)

        } catch (e: Exception) {
            Result.failure(e)
        }    }

    override suspend fun getMovie(id: Int): Result<MoviesItem> {
        return try {
            val movie = MovieDao.getMovie(id)
            Result.success(movie.toMoviesItem())
        }catch (e : Throwable){
            Result.failure(e)
        }
    }
}
