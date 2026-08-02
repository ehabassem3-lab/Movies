package com.example.movies.ui.main.tabs.home

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ComponentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.example.movies.ui.main.tabs.saved.SavedViewModel

@SuppressLint("ContextCastToActivity", "RestrictedApi")
@Composable
fun sharedHOmeViewModel(): HomeViewModel {
    val activity = LocalContext.current as ComponentActivity
    return hiltViewModel(activity as ViewModelStoreOwner)
}