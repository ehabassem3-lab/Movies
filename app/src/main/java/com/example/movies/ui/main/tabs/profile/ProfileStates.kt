package com.example.movies.ui.main.tabs.profile

import com.example.movies.ui.main.Resources


data class  ProfileStates (val apiState : Resources<Unit> = Resources.idle)

sealed class ProfileEvents{
    object  OnLogOutClick : ProfileEvents()
}