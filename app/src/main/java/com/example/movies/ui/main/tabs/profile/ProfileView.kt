package com.example.movies.ui.main.tabs.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources

@Composable
fun ProfileView(navController: NavController){
    val colorScheme = MaterialTheme.colorScheme
    val viewModel = hiltViewModel<ProfileViewModel>()
    val state = viewModel.state.collectAsState().value
    LaunchedEffect(state.apiState) {
        when(state.apiState){
            is Resources.Error -> {}
            Resources.Loading -> {}
            is Resources.Success<*> -> navController.navigate(AppRoutes.CreateSessionRoute)
            Resources.idle -> {}
        }

    }
    Column(
        modifier = Modifier.fillMaxSize().background(colorScheme.background) ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Rounded.Lock ,
            contentDescription = ""  ,
            tint =  colorScheme.onBackground ,
            modifier = Modifier.size(100.dp).clickable{
                viewModel.doAction(ProfileEvents.OnLogOutClick)
            }
        )



    }
}