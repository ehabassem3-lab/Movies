package com.example.movies.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val AppTypography = Typography(

    titleLarge = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold ,


    ),

    titleMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold ,
    ),

    titleSmall = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Light ,
    ),

    bodyMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal ,
    ),

    labelLarge = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Light ,
    )
)