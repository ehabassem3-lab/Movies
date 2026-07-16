package com.example.utilities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.suwasto.kmmcomposeshimmer.ShimmerContainer

@Composable
fun LoadingView(){
    val colorScheme = MaterialTheme.colorScheme
    Column {
        ShimmerContainer (
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .width(170.dp)
                .height(380.dp)
                .background(colorScheme.onBackground.copy(alpha = .5f), RoundedCornerShape(15.dp))

        ){
            ShimmerContainer (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(Color.Gray ,RoundedCornerShape(topEnd = 15.dp , topStart = 15.dp) )
            ){

            }
            ShimmerContainer (
                modifier = Modifier
                    .padding(top = 310.dp  , start = 10.dp , end = 10.dp)
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.Gray ,RoundedCornerShape(15.dp ))
            ){

            }

        }



    }


}