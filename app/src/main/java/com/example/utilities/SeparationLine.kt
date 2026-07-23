package com.example.utilities

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SeparationLine(
){
    val colorScheme = MaterialTheme.colorScheme
    Box(
        modifier = Modifier.height(2.dp).background(colorScheme.onBackground).fillMaxWidth()
    )

}