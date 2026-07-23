package com.example.movies.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.tabs.profile.ProfileViewModel
import com.example.movies.ui.main.tabs.saved.sharedSavedViewModel
import com.example.movies.ui.theme.AppTypography
import com.example.utilities.SeparationLine

@Composable
fun SideNavigationDrawer(
    navController: NavController
){
    val colorScheme = MaterialTheme.colorScheme
    val savedViewModel = sharedSavedViewModel()
    val state = savedViewModel.state.collectAsState().value
    val number = (state.allWatchListState as? Resources.Success)?.data?.size
    Column (
        modifier = Modifier
            .fillMaxHeight()
            .width(250.dp)
            .background(colorScheme.onBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.2f)
                .background(colorScheme.background) ,
            contentAlignment = Alignment.Center
        ) {
            Text(
                stringResource(R.string.movies_side_drawer_header),
                color =  colorScheme.onBackground ,
                textAlign = TextAlign.Center ,
                style = AppTypography.titleLarge
            )

        }
        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically ,
                horizontalArrangement = Arrangement.Center
            ) {


                Text(
                    stringResource(R.string.go_to_watch_lst),
                    style = AppTypography.titleLarge.copy(
                        color = colorScheme.background,
                        fontSize = 24.sp,
                    )
                )
                Box(
                    modifier = Modifier.size(30.dp).background(colorScheme.background , CircleShape) ,
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward ,
                        contentDescription = "" ,
                        tint = colorScheme.onBackground,
                        modifier = Modifier.size(15.dp).clickable{
                            navController.navigate(AppRoutes.WatchListRoute)
                        }
                    )
                }
            }
              Row(
                  verticalAlignment = Alignment.CenterVertically ,
                  horizontalArrangement = Arrangement.Center ,
                  modifier = Modifier.padding(10.dp)
              ){
                  Text(
                      stringResource(R.string.watch_list_count) ,
                      modifier = Modifier.padding(5.dp)
                  )
                  Text(
                     number.toString() ,
                      modifier = Modifier.padding(5.dp)

                  )
                  Icon(
                      painterResource(R.drawable.ic_un_save) ,
                      contentDescription = ""  ,
                      tint = colorScheme.background ,
                      modifier = Modifier
                          .padding(5.dp)
                          .size(24.dp)
                          .background(colorScheme.onBackground)
                      ,
                  )
              }
            Box(
                modifier = Modifier.height(2.dp).background(colorScheme.background).fillMaxWidth()
            )
        }



    }
}