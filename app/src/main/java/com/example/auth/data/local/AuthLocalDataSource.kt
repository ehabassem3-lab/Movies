package com.example.auth.data.local

import com.example.movies.ui.main.tabs.profile.UserData

interface AuthLocalDataSource {
    suspend fun  getUser() : Result<UserData>
}