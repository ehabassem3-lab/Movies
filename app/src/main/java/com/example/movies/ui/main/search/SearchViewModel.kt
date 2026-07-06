package com.example.movies.ui.main.search

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.R
import com.example.movies.domain.repositories.home.HomeRepository
import com.example.movies.domain.repositories.search.SearchRepository
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.home.HomeEvents
import com.example.movies.ui.main.tabs.home.HomeStates
import com.example.movies.ui.main.tabs.home.MovieSectionUiState
import com.example.movies.ui.main.tabs.home.TvSectionUiState
import com.example.movies.ui.main.tabs.home.sections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel

class SearchViewModel @Inject constructor(
    private val repository: SearchRepository ,
    private val homerepo  : HomeRepository
) : ViewModel(){

    val state : MutableStateFlow<SearchStates> = MutableStateFlow(SearchStates())

    fun doAction(events: SearchEvent){
        when(events){
            is   SearchEvent.onSearchClick -> searchMovies(events.search)
         is   SearchEvent.onSearchChangeing -> { state.value = state.value.copy(search =  events.search) }

            SearchEvent.loadData -> loadData()
        }

    }

    private fun loadData() {
       viewModelScope.launch {
           val tv =   homerepo.getDiscoveryTv(page =1, genre =null)
           val movie  =  homerepo.getDiscoveryMovies(page =1, genre =null)
           if (tv.isSuccess && movie.isSuccess){
               state.value = state.value.copy(
                   tvRecommendation = Resources.Success(tv.getOrNull()),
                   movieRecommendation = Resources.Success(movie.getOrNull())
               )
           }else{

           }


       }
    }


    private fun searchMovies(search : String) {
        viewModelScope.launch {
            state.value = state.value.copy(apiState = Resources.Loading)
            val response = repository.searchMovies(search)
            if (response.isSuccess){
                val data = response.getOrNull()
                state.value = state.value.copy( apiState = Resources.Success(data))
            }else{
                state.value = state.value.copy(apiState = Resources.Error(Throwable(response.exceptionOrNull())))
            }
        }
    }
}