package com.example.movies.ui.main

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.network.createHttpClient
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.tabs.home.HomeView
import com.example.movies.ui.main.tabs.profile.ProfileView
import com.example.movies.ui.main.tabs.saved.SavedView
import com.example.movies.ui.main.tabs.trending.TrendingView
import com.example.movies.ui.theme.Black
import com.example.movies.ui.theme.White
import io.ktor.client.request.get
import io.ktor.http.isSuccess
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@SuppressLint("LocalContextConfigurationRead")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController){


    var drawerState = rememberDrawerState(DrawerValue.Closed)
    val colorScheme = MaterialTheme.colorScheme
    val scope = rememberCoroutineScope()
    data class navigationItem (val index : Int , val icon : Int )
    var selectedIndex  by  rememberSaveable { mutableIntStateOf(0) }
    val colors = NavigationBarItemColors(
        selectedIconColor = colorScheme.background,
        selectedTextColor = Color.Transparent,
        selectedIndicatorColor = colorScheme.onBackground,
        unselectedIconColor = colorScheme.onBackground,
        unselectedTextColor = Color.Transparent,
        disabledIconColor = Color.Transparent,
        disabledTextColor = Color.Transparent ,
    )
    val navigationItems = listOf(
        navigationItem(0 , R.drawable.ic_home) ,
        navigationItem(1 , R.drawable.ic_trending) ,
        navigationItem( 2,R.drawable.ic_saved) ,
        navigationItem( 3,R.drawable.ic_profile) ,

    )
    ModalNavigationDrawer(
        drawerContent = { SideNavigationDrawer()},
        drawerState = drawerState,

    ) {
        Scaffold(
            topBar = {
                if(selectedIndex != 3) {
                   TobBar(
                       title = stringResource(R.string.app_name),
                       onMenuClick = {
                           scope.launch {
                               drawerState.open()

                           }
                                     },
                       onSearchClick = {
                           navController.navigate(AppRoutes.SearchRoute)
                       }

                   )
                }

            },


            bottomBar = {
                NavigationBar(
                    modifier = Modifier
                        .background( colorScheme.background, RoundedCornerShape(25.dp) ) ,
                    containerColor = Color.Transparent

                ) {
                    for (item in navigationItems ){
                        val isSelected = item.index == selectedIndex
                        NavigationBarItem(
                            colors = colors,
                            selected =  isSelected ,

                            icon = {

                                    Row(
                                        modifier = Modifier
                                            .padding( top =  if (!isSelected) 22.dp  else 0.dp)
                                            .width(26.dp)
                                            .height(26.dp)
                                            .background(
                                                color = if (isSelected) colorScheme.onBackground else Color.Transparent,
                                                shape = RoundedCornerShape(10.dp)
                                            ),

                                        verticalAlignment = Alignment.Top,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Icon(
                                            painter = painterResource(item.icon),
                                            modifier = Modifier
                                                .size(if (isSelected) 20.dp else 16.dp),
                                            contentDescription = "" ,
                                            tint = if (isSelected) colorScheme.background else colorScheme.onBackground
                                        )
                                    }



                            } ,
                            onClick = {
                                selectedIndex  = item.index
                            }
                        )
                    }

                }
            }
        )
        {
                innerPadding->
            Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                when(selectedIndex){
                    0 -> HomeView(navController)
                    1 -> TrendingView()
                    2 -> SavedView(navController)
                    3 -> ProfileView(navController)

                }

            }


        }
    }


}