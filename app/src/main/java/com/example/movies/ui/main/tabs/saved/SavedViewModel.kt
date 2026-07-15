package com.example.movies.ui.main.tabs.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.repository.AuthRepository
import com.example.movies.domain.repositories.home.HomeRepository
import com.example.movies.network.response.FavItem
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.profile.FavouriteItem
import com.example.movies.ui.main.tabs.profile.ProfileEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedViewModel @Inject constructor(
    val repository: AuthRepository ,
) : ViewModel (){
    val state : MutableStateFlow<FavStates> = MutableStateFlow(FavStates())
    init {
        doAction(FavEvents.onGetAllFav)
        doAction(FavEvents.OnGetFavouriteTv)
        doAction(FavEvents.OnGetFavouriteMovie)

    }

    fun doAction(event: FavEvents){
        when(event){
            FavEvents.OnGetFavouriteMovie -> getFavMovies()
            FavEvents.OnGetFavouriteTv -> getFavTv()
            FavEvents.onGetAllFav -> getAllFav()
        }
    }
    private fun getAllFav() {
        viewModelScope.launch {
            state.value = state.value.copy(allFavState = Resources.Loading)
            val moviesCall = async { repository.getFavouriteMovies() }
            val tvCall = async { repository.getFavouriteTv() }
            val movieResult = moviesCall.await()
            val tvResult = tvCall.await()
            if (movieResult.isSuccess && tvResult.isSuccess){
                val movies = movieResult.getOrNull()?.results?.map {
                    FavItem(
                        id = it?.id ?: 0,
                        title = it?.name ?: "",
                        posterPath = it?.posterPath,
                        mediaType = "movie",
                        overview = it?.overview,
                        backdropPath = it?.backdropPath,
                        genreIds = it?.genreIds,
                        voteAverage = it?.voteAverage,
                        voteCount = it?.voteCount,
                        popularity = it?.popularity,
                        originalLanguage = it?.originalLanguage,
                        originalTitle = it?.originalName,
                        firstAirDate = it?.firstAirDate,
                        originCountry = it?.originCountry as List<String>?,
                    )
                } ?: emptyList()

                val tv = tvResult.getOrNull()?.results?.map {
                    FavItem(
                        id = it?.id ?: 0,
                        title = it?.name ?: "",
                        posterPath = it?.posterPath,
                        mediaType = "tv",
                        overview = it?.overview,
                        backdropPath = it?.backdropPath,
                        genreIds = it?.genreIds,
                        voteAverage = it?.voteAverage,
                        voteCount = it?.voteCount,
                        popularity = it?.popularity,
                        originalLanguage = it?.originalLanguage,
                        originalTitle = it?.originalName,
                        firstAirDate = it?.firstAirDate,
                        originCountry = it?.originCountry as List<String>?,

                    )
                } ?: emptyList()
                val favourites = (movies + tv)
                    .sortedByDescending { it.id }
                state.value =state.value.copy(allFavState = Resources.Success(favourites))

            }else{
                state.value = state.value.copy(allFavState =
                    Resources.Error(Throwable("${movieResult.exceptionOrNull()}  ${tvResult.exceptionOrNull()} " )))

            }

        }


    }

    private fun getFavTv() {
        viewModelScope.launch {
            state.value = state.value.copy(FavTvState = Resources.Loading)
            val request = repository.getFavouriteTv()
            if (request.isSuccess){
                state.value = state.value.copy(FavTvState = Resources.Success(request.getOrNull()))
            }else{
                state.value = state.value.copy(FavTvState = Resources.Error(Throwable(request.exceptionOrNull())))

            }
        }


    }

    private fun getFavMovies() {
        viewModelScope.launch {
            state.value = state.value.copy(FavMovieState = Resources.Loading)
            val request = repository.getFavouriteMovies()
            if (request.isSuccess){
                state.value = state.value.copy(FavMovieState = Resources.Success(request.getOrNull()))
            }else{
                state.value = state.value.copy(FavMovieState = Resources.Error(Throwable(request.exceptionOrNull())))

            }
        }


    }

}