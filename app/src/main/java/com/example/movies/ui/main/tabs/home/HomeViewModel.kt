package com.example.movies.ui.main.tabs.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movies.domain.repositories.home.HomeRepository
import com.example.movies.ui.main.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    val state : MutableStateFlow<HomeStates> = MutableStateFlow(HomeStates())

    fun doAction(events: HomeEvents){
        when(events){
            HomeEvents.getDiscoverTv -> getDiscoverTv()
        }

    }


    fun getDiscoverTv(){
        viewModelScope.launch {
            state.value = state.value.copy(apiState = Resources.Loading)
            val response = repository.getDiscoveryTv()
            if (response.isSuccess){
                val data = response.getOrNull()
                state.value = state.value.copy( apiState = Resources.Success(data))
             }else{
                 state.value = state.value.copy(apiState = Resources.Error(Throwable(response.exceptionOrNull())))
            }
        }
    }

}