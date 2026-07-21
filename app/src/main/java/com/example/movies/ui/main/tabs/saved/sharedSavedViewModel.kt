package com.example.movies.ui.main.tabs.saved

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ComponentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner

@SuppressLint("ContextCastToActivity", "RestrictedApi")
@Composable
fun sharedSavedViewModel(): SavedViewModel {
    val activity = LocalContext.current as ComponentActivity
    return hiltViewModel(activity as ViewModelStoreOwner)
}