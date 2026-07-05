package com.example.movies.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movies.ui.theme.AppTypography
import io.ktor.sse.SPACE

@Composable
fun EmptyView(
    text : String ,
    Image : Int
){
    val colorScheme = MaterialTheme.colorScheme
    Column(
        modifier =  Modifier.fillMaxSize() ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(Image) ,
            contentDescription = "" ,
            modifier = Modifier.size(200.dp)  ,
            tint = colorScheme.onBackground
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text ,
            style = AppTypography.titleSmall.copy(color =colorScheme.onBackground, fontWeight = FontWeight.Light)
            )

    }

}