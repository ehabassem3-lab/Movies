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
        }

    }

    private fun createRequest() {
        viewModelScope.launch {
            state.value =state.value.copy(createRequest = Resources.Loading)
            val request = repository.createRequestToken()
            if (request.isSuccess){
                val response =request.getOrNull()
                println(response)
                state.value =  state.value.copy(createRequest = Resources.Success(response))
            }else{
                state.value =  state.value.copy(createRequest = Resources.Error(Throwable(request.exceptionOrNull())))
            }



        }
    }
}