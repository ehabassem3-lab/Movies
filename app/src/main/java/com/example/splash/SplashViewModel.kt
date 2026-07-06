package com.example.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
         val state : MutableStateFlow<SplashStates> = MutableStateFlow(SplashStates())


    fun doAction(events: SplashEvents){
        when(events){
            SplashEvents.checkIsLoggedIn -> checkUserLoggedIn()
        }

    }

    private fun checkUserLoggedIn() {
        viewModelScope.launch {

            val status = authRepository.isLoggedIn()

            state.value = state.value.copy(
                isLoading = false,
                isLoggedIn = status.isSuccess
            )
        }
    }


}