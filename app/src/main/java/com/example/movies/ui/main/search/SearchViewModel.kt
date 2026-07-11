package com.example.movies.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.repositories.home.HomeRepository
import com.example.movies.domain.repositories.search.SearchRepository
import com.example.movies.ui.main.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class SearchViewModel @Inject constructor(
    private val repository: SearchRepository ,
    private val homerepo  : HomeRepository
) : ViewModel(){

    val state : MutableStateFlow<SearchStates> = MutableStateFlow(SearchStates())

    fun doAction(events: SearchEvent){
        when(events){
            is   SearchEvent.onSearchClick -> searchMovies(events.search , events.page )
         is   SearchEvent.onSearchChangeing -> { state.value = state.value.copy(search =  events.search) }

            SearchEvent.loadData -> loadData()
        }

    }
    init {
        doAction(SearchEvent.loadData)
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


    private fun searchMovies(search: String, page: Int) {
        viewModelScope.launch {
            state.value = state.value.copy(apiState = Resources.Loading)
            val response = repository.searchMovies(search, page)
            if (response.isSuccess){
                delay(3000)
                val data = response.getOrNull()
                state.value = state.value.copy( apiState = Resources.Success(data))
            }else{
                state.value = state.value.copy(apiState = Resources.Error(Throwable(response.exceptionOrNull())))
            }
        }
    }
}