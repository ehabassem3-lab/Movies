package com.example.movies.ui.main.actor

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movies.domain.repositories.home.HomeRepository
import com.example.movies.ui.main.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorViewModel @Inject constructor(
    val repository: HomeRepository
): ViewModel() {
      val states  :  MutableStateFlow<ActorStates> = MutableStateFlow(ActorStates())

    fun doAction(event : ActorEvents){
        when(event){
            is ActorEvents.onGetActor -> getActor(event.id)
            is ActorEvents.onGetActorWork -> getActorWork(event.id)
        }

    }

    private fun getActorWork(id: Int) {

        viewModelScope.launch {
            states.value = states.value.copy(workState = Resources.Loading)
            val work = repository.getActorWork(id)
            if (work.isSuccess){
                states.value = states.value.copy(workState = Resources.Success(work.getOrNull()))
            }else{
                states.value = states.value.copy(workState = Resources.Error(Throwable(work.exceptionOrNull())))
            }
        }
    }

    private fun getActor(id: Int) {
        viewModelScope.launch {
            states.value =states.value.copy(actorState = Resources.Loading)
            val actor = repository.getActor(id)
            if (actor.isSuccess){
                states.value = states.value.copy(actorState = Resources.Success(actor.getOrNull()))

            }else{
                states.value = states.value.copy(actorState = Resources.Error(Throwable(actor.exceptionOrNull())))
            }
        }
    }

}