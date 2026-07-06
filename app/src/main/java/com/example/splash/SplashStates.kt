package com.example.splash

data class SplashStates(
    val isLoading: Boolean = true,
    val isLoggedIn: Boolean = false
)
sealed class SplashEvents{
 object  checkIsLoggedIn  : SplashEvents()
}