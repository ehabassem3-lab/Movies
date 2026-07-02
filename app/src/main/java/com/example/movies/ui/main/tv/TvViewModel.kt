package com.example.movies.ui.main.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.repositories.home.HomeRepository
import com.example.movies.ui.main.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
@HiltViewModel
class TvViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel()  {
     val state : MutableStateFlow<TvStates> = MutableStateFlow(TvStates())

    fun doAction(events: TvEvents){
        when(events){
            is TvEvents.GetMovie -> getMovie(events.id)
            is TvEvents.GetTv -> getTv(events.id)
        }

    }

    private fun getTv(id: Int) {
        viewModelScope.launch {
            state.value = state.value.copy(tvApi = Resources.Loading)
            val request = repository.getTvById(id)
            if(request.isSuccess){
                state.value = state.value.copy(tvApi = Resources.Success(request.getOrNull()))

            }else{
                state.value = state.value.copy(tvApi = Resources.Error(Throwable(request.exceptionOrNull())))
            }


        }

    }

    private fun getMovie(id: Int) {
        viewModelScope.launch {
            state.value = state.value.copy(moviesApi = Resources.Loading)
            val request = repository.getMovieById(id)
            if(request.isSuccess){
                state.value = state.value.copy(moviesApi = Resources.Success(request.getOrNull()))

            }else{
                state.value = state.value.copy(moviesApi = Resources.Error(Throwable(request.exceptionOrNull())))
            }


        }
    }


}