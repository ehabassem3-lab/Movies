package com.example.auth.ui

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.ui.theme.AppTypography

@Composable
fun CreateSession(navController: NavController){
    val colorScheme = MaterialTheme.colorScheme
       val viewModel = hiltViewModel<AuthViewModel>()
    val state = viewModel.state.collectAsState().value
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
                             .background(colorScheme.onBackground , RoundedCornerShape(15.dp))
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


    }

}