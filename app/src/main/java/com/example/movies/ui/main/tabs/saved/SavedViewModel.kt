package com.example.movies.ui.main.tabs.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.repository.AuthRepository
import com.example.movies.domain.repositories.home.HomeRepository
import com.example.movies.network.response.FavItem
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.profile.FavouriteItem
import com.example.movies.ui.main.tabs.profile.ProfileEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SavedViewModel @Inject constructor(
    val repository: AuthRepository ,
    val homeRepository: HomeRepository
) : ViewModel (){
    val state : MutableStateFlow<FavStates> = MutableStateFlow(FavStates())
    init {
        doAction(FavEvents.onGetAllFav)
        doAction(FavEvents.OnGetFavouriteTv)
        doAction(FavEvents.OnGetFavouriteMovie)
        doAction(FavEvents.onGetAllWatchList)
        doAction(FavEvents.OnGetWatchListMovie)
        doAction(FavEvents.OnGetWatchListTv)
    }


    fun doAction(event: FavEvents) {
        when (event) {
            FavEvents.OnGetFavouriteMovie -> getFavMovies()
            FavEvents.OnGetFavouriteTv -> getFavTv()
            FavEvents.onGetAllFav -> getAllFav()
          is  FavEvents.onAddToWatchList -> addToWatchList(event.mediaId, event.mediaType, event.watchList,event.item)
            is FavEvents.addToFavoutire -> addFavourite(event.mediaId, event.mediaType, event.favorite, event.item)
            FavEvents.OnGetWatchListMovie -> getWatchListMovies()
            FavEvents.OnGetWatchListTv -> getWatchListTv()
            FavEvents.onGetAllWatchList -> getAllWatchList()
        }
    }
    private fun getWatchListTv(){
        viewModelScope.launch {
            state.value = state.value.copy(WatchListTvState = Resources.Loading)
            val request = homeRepository.getWatchListTv()
            if (request.isSuccess){
                state.value = state.value.copy( WatchListTvState=Resources.Success(request.getOrNull()))
            }else{
                state.value = state.value.copy(WatchListTvState = Resources.Error(Throwable(request.exceptionOrNull())))
            }
        }
    }
    private fun getWatchListMovies(){
        viewModelScope.launch {
            state.value = state.value.copy(WatchMovieState =  Resources.Loading)
            val request = homeRepository.getWatchListMovie()
            if (request.isSuccess){
                state.value = state.value.copy( WatchMovieState=Resources.Success(request.getOrNull()))
            }else{
                state.value = state.value.copy(WatchMovieState = Resources.Error(Throwable(request.exceptionOrNull())))
            }
        }
    }
    private fun getAllWatchList(){
        viewModelScope.launch {
            state.value = state.value.copy(allWatchListState = Resources.Loading)
            val moviesCall = async { homeRepository.getWatchListMovie() }
            val tvCall = async { homeRepository.getWatchListTv() }
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
                val list = (movies + tv)
                    .sortedByDescending { it.id }
                state.value =state.value.copy(allWatchListState = Resources.Success(list))
            }else{
                delay(2000)
                state.value = state.value.copy(allWatchListState =
                    Resources.Error(Throwable("${movieResult.exceptionOrNull()}  ${tvResult.exceptionOrNull()} " )))

            }
        }

    }
    private fun addToWatchList(mediaId: Int, mediaType: String, watchList: Boolean , item: DiscoverItem?) {
        viewModelScope.launch {
            state.value =state.value.copy(watchListState = Resources.Loading)
            val request = homeRepository.addToWatchList(mediaId,mediaType,watchList)
            if (request.isSuccess){
                state.value =state.value.copy(watchListState = Resources.Success(request.getOrNull()))
                val currentList = (state.value.allWatchListState as?  Resources.Success)?.data ?: emptyList()
                val updatedList = if (watchList){
                         if ( item != null &&currentList.none{it.id == mediaId && it.mediaType == mediaType}){
                             val newFavItem = FavItem(
                                 id = item.id ?: mediaId,
                                 title = item.name ?: "",
                                 posterPath = item.posterPath,
                                 mediaType = mediaType,
                                 overview = item.overview,
                                 backdropPath = item.backdropPath,
                                 genreIds = item.genreIds,
                                 voteAverage = item.voteAverage,
                                 voteCount = item.voteCount,
                                 popularity = item.popularity,
                                 originalLanguage = item.originalLanguage,
                                 originalTitle = item.originalName,
                                 firstAirDate = item.firstAirDate,
                                 originCountry = item.originCountry as? List<String>,
                             )
                             listOf(newFavItem) + currentList
                         }else{
                             currentList
                         }
                }else{
                    currentList.filterNot { it.id == mediaId && it.mediaType == mediaType }
                }
                state.value = state.value.copy(allWatchListState = Resources.Success(updatedList))
            }else{
                state.value =state.value.copy(watchListState = Resources.Error(Throwable(request.exceptionOrNull())))
            }
        }
    }

    private fun addFavourite(mediaId: Int, mediaType: String, favorite: Boolean, item: DiscoverItem?) {
        viewModelScope.launch {
            state.value = state.value.copy(favApiState = Resources.Loading)
            val request = homeRepository.addToFavorite(mediaId, mediaType, favorite)
            if (request.isSuccess) {
                state.value = state.value.copy(favApiState = Resources.Success(request.getOrNull()))

                val currentList = (state.value.allFavState as? Resources.Success)?.data ?: emptyList()

                val updatedList = if (favorite) {
                    if (item != null && currentList.none { it.id == mediaId && it.mediaType == mediaType }) {
                        val newFavItem = FavItem(
                            id = item.id ?: mediaId,
                            title = item.name ?: "",
                            posterPath = item.posterPath,
                            mediaType = mediaType,
                            overview = item.overview,
                            backdropPath = item.backdropPath,
                            genreIds = item.genreIds,
                            voteAverage = item.voteAverage,
                            voteCount = item.voteCount,
                            popularity = item.popularity,
                            originalLanguage = item.originalLanguage,
                            originalTitle = item.originalName,
                            firstAirDate = item.firstAirDate,
                            originCountry = item.originCountry as? List<String>,
                        )
                        listOf(newFavItem) + currentList
                    } else {
                        currentList
                    }
                } else {
                    currentList.filterNot { it.id == mediaId && it.mediaType == mediaType }
                }

                state.value = state.value.copy(allFavState = Resources.Success(updatedList))
            } else {
                state.value = state.value.copy(favApiState = Resources.Error(Throwable(request.exceptionOrNull())))
            }
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
                delay(2000)
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
                delay(2000)
                state.value = state.value.copy(FavTvState = Resources.Success(request.getOrNull()))
            }else{
                delay(2000)
                state.value = state.value.copy(FavTvState = Resources.Error(Throwable(request.exceptionOrNull())))

            }
        }


    }

    private fun getFavMovies() {
        viewModelScope.launch {
            state.value = state.value.copy(FavMovieState = Resources.Loading)
            val request = repository.getFavouriteMovies()
            delay(2000)
            if (request.isSuccess){
                state.value = state.value.copy(FavMovieState = Resources.Success(request.getOrNull()))
            }else{
                state.value = state.value.copy(FavMovieState = Resources.Error(Throwable(request.exceptionOrNull())))

            }
        }


    }

}