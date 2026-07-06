package com.example.movies.ui.main.tabs.profile

import android.app.AlertDialog
 import androidx.compose.material3.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.AlertDialogExample
import com.example.movies.ui.main.Resources
import com.example.movies.ui.theme.AppTypography

@Composable
fun ProfileView(navController: NavController){
    val colorScheme = MaterialTheme.colorScheme
    val viewModel = hiltViewModel<ProfileViewModel>()
    val state = viewModel.state.collectAsState().value
    val user = (state.localState as? Resources.Success)?.data
    LaunchedEffect(Unit) {
        viewModel.doAction(ProfileEvents.OnGetUserData)
    }
    LaunchedEffect(state.apiState) {
        when(state.apiState){
            is Resources.Error -> {}
            Resources.Loading -> {}
            is Resources.Success<*> -> navController.navigate(AppRoutes.CreateSessionRoute)
            Resources.idle -> {}
        }

    }
    Scaffold() { innerPadding->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorScheme.background) ,
        ) {
            println("user =  ${user}")
            when{
                state.openAlertDialog -> AlertDialogExample(
                    onDismissRequest = { viewModel.doAction(ProfileEvents.OnDismissRequest) },
                    onConfirmation = {  viewModel.doAction(ProfileEvents.OnLogOutClick) },
                    dialogTitle = "LOG OUT ",
                    dialogText = " Are you Sure of Login Out ",
                    icon = painterResource(R.drawable.ic_profile)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp) ,
                verticalAlignment = Alignment.CenterVertically ,

            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_logout),
                    contentDescription = ""  ,
                    tint =  colorScheme.onBackground ,

                    modifier = Modifier
                        .padding(start = 20.dp, top = 10.dp)
                        .size(24.dp)
                        .clickable {
                            viewModel.doAction(ProfileEvents.OnOpenRequest)
                        }
                )
                Text(
                    stringResource(R.string.profile),
                    modifier = Modifier.padding(start = 120.dp),
                    style = AppTypography.titleLarge.copy(fontSize = 30.sp )
                )

            }




        }

    }

}