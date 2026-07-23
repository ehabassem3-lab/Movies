package com.example.movies.ui.main.watchlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.theme.AppTypography

@Composable
fun WatchList(
    navController: NavController
){
    val colorScheme = MaterialTheme.colorScheme
    Column(
        modifier = Modifier.fillMaxSize().background(colorScheme.background).padding(top = 30.dp , start = 20.dp  , end = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack ,
                contentDescription = "" ,
                tint = colorScheme.onBackground ,
                modifier = Modifier.clickable{
                    navController.navigate(AppRoutes.MainRoute)
                }

                )
            Box(
                modifier = Modifier.fillMaxWidth().height(60.dp) ,
                contentAlignment = Alignment.Center


            ) {
                Text(
                    "Watch List",
                    textAlign = TextAlign.Center,
                    style = AppTypography.titleLarge.copy(
                        color = colorScheme.onBackground ,
                        fontSize = 26.sp
                    ) ,

                    )

            }
        }


    }

}