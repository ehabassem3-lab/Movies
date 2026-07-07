package com.example.utilities

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.movies.R
import com.example.movies.ui.theme.AppTypography

@Composable
fun ErrorView(onRetry : () -> Unit){
    val colorScheme = MaterialTheme.colorScheme
    Column(
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.error_header) ,
            style = AppTypography.titleLarge.copy(color = colorScheme.onBackground) ,
            modifier = Modifier.padding(vertical = 30.dp)

        )
        Box(modifier = Modifier
            .fillMaxWidth(.8f)
            .background(colorScheme.onBackground, RoundedCornerShape(12.dp))
            .height(50.dp) ,
            contentAlignment = Alignment.Center
        )

        {
            Text(
                stringResource(R.string.retry),
                style = AppTypography.titleLarge.copy(color = colorScheme.background) ,
                modifier = Modifier.clickable{
                    onRetry()
                }

            )

        }

    }

}