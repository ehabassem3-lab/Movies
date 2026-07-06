package com.example.movies.ui.main.tabs.profile

import com.example.movies.ui.main.Resources


data class  ProfileStates (
    val apiState : Resources<Unit> = Resources.idle ,
    val openAlertDialog : Boolean = false ,
    val user : UserData? = null ,
    val localState : Resources<UserData> = Resources.idle

)

sealed class ProfileEvents{
    object  OnLogOutClick : ProfileEvents()
    object OnDismissRequest : ProfileEvents()
    object OnOpenRequest : ProfileEvents()
    object OnGetUserData : ProfileEvents()
}