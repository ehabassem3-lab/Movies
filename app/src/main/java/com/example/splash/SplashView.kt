package com.example.splash


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movies.App
import com.example.movies.R
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.Resources
import com.example.movies.ui.main.tabs.home.HomeEvents
import com.example.movies.ui.main.tabs.home.HomeViewModel
import com.example.movies.ui.main.tabs.home.TvSectionUiState
import kotlinx.coroutines.delay
import java.util.prefs.Preferences

@Composable
fun SplashView  (navController: NavController) {
    val colorScheme = MaterialTheme.colorScheme
    val logoScale = remember { Animatable(1f) }
    val textScale = remember { Animatable(0f) }
    val viewModel = hiltViewModel<SplashViewModel>()
    val state = viewModel.state.collectAsState().value
    var moveLogo by remember { mutableStateOf(false) }
    var showText by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        viewModel.doAction(SplashEvents.checkIsLoggedIn)
        logoScale.animateTo(
            targetValue = 2f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
        moveLogo = true
        delay(300)
        showText = true


        textScale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )

        delay(3000)
    }
    LaunchedEffect(state.isLoading) {
        if (!state.isLoading) {
            if (state.isLoggedIn ) {
                delay(2000)
                navController.navigate(AppRoutes.MainRoute())
            } else {
                navController.navigate(AppRoutes.CreateSessionRoute)
            }
        }
    }

    val logoOffset by animateDpAsState(
        targetValue = if (moveLogo) (-90).dp else 0.dp,
        animationSpec = tween(700),
        label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            painter = painterResource(R.drawable.ic_splash_edited),
            contentDescription = null,
            tint = colorScheme.onBackground,
            modifier = Modifier
                .offset(y = logoOffset)
                .size(225.dp * logoScale.value)
        )

        if (showText) {

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge.copy(color = colorScheme.onBackground , fontSize = 32.sp),
                color = colorScheme.onBackground,
                modifier = Modifier
                    .offset(y = -180.dp)
                    .graphicsLayer {
                        scaleX = textScale.value
                        scaleY = textScale.value
                    }
            )
        }
    }
}