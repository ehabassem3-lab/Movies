package com.example.utilities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.suwasto.kmmcomposeshimmer.ShimmerContainer

@Composable
fun LoadingView(){

    ShimmerContainer (
      modifier = Modifier
          .padding(horizontal = 8.dp, vertical = 8.dp)
          .width(200.dp)
          .height(380.dp)

    ){

    }

}