package com.example.movies.ui.main

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import android.media.Image
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movies.R
import com.example.movies.ui.theme.Black
import com.example.movies.ui.theme.White
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

@Composable
fun HomeView(navController: NavController){
    data class navigationItem (val index : Int , val icon : Int )
    var selectedIndex  by  remember { mutableIntStateOf(0) }
    val navigationItems = listOf(
        navigationItem(0 , R.drawable.ic_video) ,
        navigationItem(1 , R.drawable.ic_video) ,
        navigationItem( 2,R.drawable.ic_video) ,
        navigationItem( 3,R.drawable.ic_video) ,

    )
    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier.background(Black, RoundedCornerShape(25.dp))
            ) {
                for (item in navigationItems ){
                    val isSelected = item.index == selectedIndex
                    NavigationBarItem(
                        selected =  isSelected ,
                        icon = {
                            Row(
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(40.dp)
                                    .background(
                                        color = if (isSelected) White else Black,
                                        shape = RoundedCornerShape(26.dp)
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    painter = painterResource(item.icon),
                                    modifier = Modifier
                                        .size(if (isSelected) 26.dp else 22.dp),
                                    contentDescription = "" ,
                                    tint = if (isSelected) Black else White
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

        }


    }

}