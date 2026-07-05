package com.example.features.movies.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movies.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TobBar(
    title: String,
    onMenuClick: () -> Unit ,
    onSearchClick: () -> Unit
){
    val colorScheme = MaterialTheme.colorScheme
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        navigationIcon = {
            Image(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .size(30.dp)
                    .clickable(onClick = onMenuClick),
                painter = painterResource(R.drawable.ic_hambruger),
                contentDescription = "Menu",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
        },
        actions = {
            Image(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size(30.dp)
                    .clickable{
                        onSearchClick()
                    },
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "Search",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = Color.Unspecified,
            navigationIconContentColor = Color.Unspecified,
            titleContentColor = Color.Unspecified,
            actionIconContentColor = Color.Unspecified
        ))

}