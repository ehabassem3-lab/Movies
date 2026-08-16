package com.example.movies.data.datasource.home

import com.example.movies.mapper.toEntity
import com.example.movies.mapper.toMoviesItem
import com.example.movies.mapper.toTvItem
import com.example.movies.network.response.cast.Actor
import com.example.movies.network.response.cast.ActorWork
import com.example.movies.network.response.cast.Cast
import com.example.movies.network.response.cast.CastItem
import com.example.movies.network.response.discover.DiscoverItem
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

    override suspend fun getTv(id: Int): Result<DiscoverItem> {
        return try {
            val tv = TvDao.getTv(id)
            Result.success(tv.toTvItem())
        }catch (e : Throwable){
            Result.failure(e)
        }
    }

    override suspend fun getCast(): Result<Cast> {
        TODO("Not yet implemented")
    }

    override suspend fun saveCast(cast: List<CastItem>) {
      val cast = TvDao.saveCast(cast)


    }

    override suspend fun savePerson(actor: Actor): Result<Unit> {
       val person  =TvDao.savePerson(actor)

        return Result.success(Unit)

    }

    override suspend fun savePersonWork(actor: ActorWork): Result<Unit> {
        val person  =TvDao.savePersonWork(actor)
        return Result.success(Unit)
    }

    override suspend fun getPerson(id: Int): Result<Actor> {
         val request = TvDao.getPerson(id)
        return if (request.isSuccess){
            Result.success(request.getOrNull()!!)
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }

    override suspend fun getPersonWork(id: Int): Result<ActorWork> {
       val request = TvDao.getPersonWork(id)
        return if (request.isSuccess){
            Result.success(request.getOrNull()!!)
        }else{
            Result.failure(Throwable(request.exceptionOrNull()))
        }
    }
}
