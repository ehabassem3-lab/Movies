package com.example.movies.ui.main.tabs.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.repositories.home.HomeRepository
import com.example.movies.ui.main.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(
    val repository: HomeRepository
) : ViewModel(){
    val state : MutableStateFlow<TrendingState> = MutableStateFlow(TrendingState())

    fun doAction(event: TrendingEvents){
        when(event){
            TrendingEvents.getMovies -> getMovies()
            TrendingEvents.getTv ->  getTv()
        }

    }
    init {
        doAction(TrendingEvents.getTv)
        doAction(TrendingEvents.getMovies)
    }

    private fun getTv() {
        viewModelScope.launch {
            state.value = state.value.copy(TvapiState = Resources.Loading)
            val request = repository.getTvTrending()
            if (request.isSuccess){
                state.value =state.value.copy(TvapiState = Resources.Success(request.getOrNull()))
            }else{
                state.value = state.value.copy(TvapiState = Resources.Error(Throwable(request.exceptionOrNull())))
            }
        }

    }

    private fun getMovies() {
        viewModelScope.launch {
            state.value = state.value.copy(MoviesapiState = Resources.Loading)
            val request = repository.getMoviesTrending()
            if (request.isSuccess){
                state.value =state.value.copy(MoviesapiState = Resources.Success(request.getOrNull()))
            }else{
                state.value = state.value.copy(MoviesapiState = Resources.Error(Throwable(request.exceptionOrNull())))
            }
        }

    }

}