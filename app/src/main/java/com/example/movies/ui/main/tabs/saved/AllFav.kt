package com.example.movies.ui.main.tabs.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.profile.FavouriteItem

@Composable
fun Allfav(
    state: Resources<List<FavouriteItem>>
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        when(state){
            is Resources.Error -> {}
            Resources.Loading -> {}
             is Resources.Success<*> ->{



            }
            Resources.idle -> {}
        }
    }


}