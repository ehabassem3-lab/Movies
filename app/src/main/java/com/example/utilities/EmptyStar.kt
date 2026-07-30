package com.example.utilities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.movies.R

@Composable
fun EmptyStar(){
    val colorScheme = MaterialTheme.colorScheme
    Box(
        modifier = Modifier.size(30.dp)
    ){
        Icon(
            painter = painterResource(R.drawable.ic_empt_star) ,
            contentDescription = "" ,
            tint = colorScheme.onBackground ,
            modifier = Modifier
                .matchParentSize()
        )
    }
}