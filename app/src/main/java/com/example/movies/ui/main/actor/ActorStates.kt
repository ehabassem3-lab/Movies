package com.example.movies.ui.main.actor

import com.example.movies.network.response.cast.Actor
import com.example.movies.network.response.cast.ActorWork
import com.example.movies.ui.main.Resources

data class ActorStates (
    val actorState : Resources<Actor> = Resources.idle ,
    val workState : Resources<ActorWork> = Resources.idle

)

sealed class  ActorEvents{
    data class onGetActor (val id : Int) : ActorEvents()
    data class onGetActorWork (val id :  Int) : ActorEvents()
}
