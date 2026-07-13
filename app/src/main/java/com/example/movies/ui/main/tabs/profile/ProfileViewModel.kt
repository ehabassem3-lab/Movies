package com.example.movies.ui.main.tabs.profile

import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.auth.domain.repository.AuthRepository
import com.example.movies.ui.main.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private  val authRepository: AuthRepository ,
) : ViewModel() {
    val state : MutableStateFlow<ProfileStates> = MutableStateFlow(ProfileStates())


    init {
        doAction(ProfileEvents.OnGetUserData)
        Log.d("ProfileView", "Composed")
        doAction(ProfileEvents.OnGetFavouriteTv)
        doAction(ProfileEvents.OnGetFavouriteMovie)

    }


    fun doAction( events  : ProfileEvents){
        when(events){
            ProfileEvents.OnLogOutClick -> logOut()
            ProfileEvents.OnDismissRequest -> {state.value = state.value.copy(openAlertDialog = false)}
            ProfileEvents.OnOpenRequest -> {state.value = state.value.copy(openAlertDialog = true)}
            ProfileEvents.OnGetUserData -> getUserData()
             ProfileEvents.OnGetFavouriteMovie -> getFavMovies()
            ProfileEvents.OnGetFavouriteTv ->  getFavTv()
            ProfileEvents.onGetAllFav -> getAllFav()
        }

    }

    private fun getAllFav() {


    }

    private fun getFavTv() {
        viewModelScope.launch {
            state.value = state.value.copy(FavTvState = Resources.Loading)
            val request = authRepository.getFavouriteTv()
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
            val request = authRepository.getFavouriteMovies()
            if (request.isSuccess){
                state.value = state.value.copy(FavMovieState = Resources.Success(request.getOrNull()))
            }else{
                state.value = state.value.copy(FavMovieState = Resources.Error(Throwable(request.exceptionOrNull())))

            }
        }


    }

    private fun getUserData() {
        viewModelScope.launch {
            state.value =state.value.copy(localState = Resources.Loading)
            val data = authRepository.getUser()
            if(data.isSuccess){
              state.value = state.value.copy(localState = Resources.Success(data.getOrNull()!!))
            }else{
             state.value = state.value.copy(localState = Resources.Error(Throwable(data.exceptionOrNull())))
            }

        }
    }

    private fun logOut() {
        viewModelScope.launch {
            state.value = state.value.copy(apiState = Resources.Loading)
            val request = authRepository.LogOut()
            if (request.isSuccess){
                state.value  =state.value.copy(apiState = Resources.Success(Unit))
            }else{
                state.value = state.value.copy(apiState = Resources.Error(Throwable(request.exceptionOrNull())))
            }
        }
    }
}