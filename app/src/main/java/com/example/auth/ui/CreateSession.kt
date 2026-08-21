package com.example.auth.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.ui.main.Resources
import com.example.movies.ui.theme.AppTypography
import androidx.core.net.toUri
import com.example.movies.routes.AppRoutes

@Composable
fun CreateSession(navController: NavController , deepLink : Uri?){
    val requestToken = deepLink?.getQueryParameter("request_token")
    val approved = deepLink?.getQueryParameter("approved") == "true"
    val colorScheme = MaterialTheme.colorScheme
    val viewModel = hiltViewModel<AuthViewModel>()
    val state = viewModel.state.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(state.createRequest) {
        val success = state.createRequest as? Resources.Success ?: return@LaunchedEffect
        val token = success.data?.requestToken ?: return@LaunchedEffect

        val intent = Intent(
            Intent.ACTION_VIEW,
            "https://www.themoviedb.org/authenticate/$token?redirect_to=movies://auth".toUri()
        )

        context.startActivity(intent)
    }
    LaunchedEffect(requestToken, approved) {
        if (approved && requestToken != null) {
            viewModel.doAction(
                AuthEvents.OnCreateSession(requestToken)
            )
        }
    }
    LaunchedEffect(state.createSession) {
        val session = state.createSession as? Resources.Success ?: return@LaunchedEffect

        val sessionId = session.data?.sessionId ?: return@LaunchedEffect

        viewModel.doAction(
            AuthEvents.OnGetAccount(sessionId)
        )
    }


        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background) ,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                , verticalArrangement = Arrangement.Center ,
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                when (state.uiState) {
                    AuthUiState.CreatingSession ->    {
                        CircularProgressIndicator(modifier = Modifier
                            .padding(vertical = 30.dp)
                            .size(100.dp) , color = colorScheme.onBackground)
                        Text(stringResource(R.string.we_are_creating_your_session), style = AppTypography.titleLarge.copy(color = colorScheme.onBackground  , fontSize =  40.sp))
                    }
                    is AuthUiState.Error -> {}
                    AuthUiState.Idle -> {
                        Column(
                            modifier = Modifier.size(500.dp) ,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Text(
                                stringResource(R.string.app_name) ,
                                style = AppTypography.titleLarge.copy(
                                    fontSize = 62.sp
                                ),
                                modifier = Modifier.padding(bottom = 40.dp) ,

                                )
                            Text(
                                stringResource(R.string.app_auth_descreption) ,
                                style = AppTypography.titleLarge.copy(
                                    fontSize = 22.sp  ,
                                    fontWeight = FontWeight.Light
                                ) ,
                                modifier = Modifier.padding(bottom = 40.dp) ,
                            )
                            Spacer(modifier = Modifier.size(40.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(.8f)
                                    .height(70.dp)
                                    .background(colorScheme.onBackground, RoundedCornerShape(15.dp))
                                    .clickable {
                                        viewModel.doAction(AuthEvents.OnCreateRequest)
                                    },
                                contentAlignment = Alignment.Center

                            ){
                                Text(
                                    stringResource(R.string.continue_with_tmdb) ,
                                    style = AppTypography.titleLarge.copy(fontSize = 22.sp , color = colorScheme.background)
                                )

                            }




                        }
                    }
                    AuthUiState.LoadingAccount ->{
                        CircularProgressIndicator(modifier = Modifier
                            .padding(vertical = 30.dp)
                            .size(100.dp) , color = colorScheme.onBackground)
                        Text(stringResource(R.string.loading_account), style = AppTypography.titleLarge.copy(color = colorScheme.onBackground  , fontSize =  40.sp))

                    }
                    AuthUiState.Success -> {
                        navController.navigate(AppRoutes.MainRoute())
                    }
                    AuthUiState.WaitingForApproval -> {
                        Text(stringResource(R.string.approve_browser) , style = AppTypography.titleLarge.copy(color = colorScheme.onBackground  , fontSize =  50.sp))
                    }
                }
            }


        }




}