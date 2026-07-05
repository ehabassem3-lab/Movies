package com.example.movies.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.repositories.search.SearchRepository
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.home.HomeEvents
import com.example.movies.ui.main.tabs.home.HomeStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel(){
    val state : MutableStateFlow<SearchStates> = MutableStateFlow(SearchStates())

    fun doAction(events: SearchEvent){
        when(events){
            is   SearchEvent.onSearchClick -> searchMovies(events.search)
         is   SearchEvent.onSearchChangeing -> {
                state.value = state.value.copy(search =  events.search)
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