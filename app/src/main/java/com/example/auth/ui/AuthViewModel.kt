package com.example.auth.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.domain.repository.AuthRepository
import com.example.movies.ui.main.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
)  : ViewModel(){
    val  state : MutableStateFlow<AuthStates> = MutableStateFlow(AuthStates())


    fun doAction(events: AuthEvents){
        when(events){
            AuthEvents.OnCreateRequest -> createRequest()
            is AuthEvents.OnCreateSession -> onCreateSession(events.requestToken)
            is AuthEvents.OnGetAccount -> onGetAccount(events.sessionId)
        }

    }

    private fun onGetAccount(sessionId: String) {
        viewModelScope.launch {
            state.value = state.value.copy(  uiState = AuthUiState.LoadingAccount, getAccount = Resources.Loading)
            val request = repository.getAccount(sessionId)
            if (request.isSuccess){
                val response = request.getOrNull()
                println(response)
                state.value = state.value.copy(    uiState = AuthUiState.Success,getAccount = Resources.Success(response))
            }else{
                state.value = state.value.copy(getAccount = Resources.Error(Throwable(request.exceptionOrNull())))
            }
        }


    }

    private fun onCreateSession(requestToken: String) {
        viewModelScope.launch {
            state.value = state.value.copy(
                uiState = AuthUiState.CreatingSession,
                createSession = Resources.Loading
            )
            val request = repository.createSession(requestToken)
            if(request.isSuccess){
                val response = request.getOrNull()
                state.value = state.value.copy(createSession = Resources.Success(response))

            }else{
                state.value = state.value.copy(createSession = Resources.Error(Throwable(request.exceptionOrNull())))
            }

        }
    }

    private fun createRequest() {
        viewModelScope.launch {
            state.value =state.value.copy(createRequest = Resources.Loading)
            val request = repository.createRequestToken()
            if (request.isSuccess){
                val response =request.getOrNull()
                println(response)
                state.value =  state.value.copy(
                    createRequest = Resources.Success(response) ,
                    uiState = AuthUiState.WaitingForApproval
                )
            }else{
                state.value =  state.value.copy(createRequest = Resources.Error(Throwable(request.exceptionOrNull())))
            }



        }
    }
}