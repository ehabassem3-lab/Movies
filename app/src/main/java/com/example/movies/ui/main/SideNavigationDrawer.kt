package com.example.movies.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SideNavigationDrawer(){
    val colorScheme = MaterialTheme.colorScheme
    Column (
        modifier = Modifier.fillMaxHeight().width(200.dp).background(colorScheme.onBackground)
    ) {

    }
}