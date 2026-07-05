package com.example.movies.ui.main.tabs.profile

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
    private  val authRepository: AuthRepository
) : ViewModel() {
    val state : MutableStateFlow<ProfileStates> = MutableStateFlow(ProfileStates())



    fun doAction( events  : ProfileEvents){
        when(events){
            ProfileEvents.OnLogOutClick -> logOut()
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