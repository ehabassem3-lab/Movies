package com.example.movies.ui.main.tabs.profile

import com.example.auth.network.response.Avatar

data class UserData (
    val name : String  ,
    val username : String ,
    val avatar: String ,
     val  isIncludedAdult : Boolean
)
