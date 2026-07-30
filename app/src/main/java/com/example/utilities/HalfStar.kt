package com.example.utilities

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movies.R

@Composable
fun HalfStar(){
    val colorScheme = MaterialTheme.colorScheme
    Box(
        modifier = Modifier.size(30.dp)
    ){
        Icon(
            painter = painterResource(R.drawable.ic_half_star) ,
            contentDescription = "" ,
            tint = Color.Yellow ,
            modifier = Modifier
                .fillMaxSize()
        )

    }
}
