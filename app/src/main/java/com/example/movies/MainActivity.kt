package com.example.movies

import android.app.LocaleManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.example.auth.ui.CreateSession
import com.example.connection.AndroidConnectivityObserver
import com.example.connection.ConnectivityViewModel
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.MainScreen
import com.example.movies.ui.main.movies.MoviesFullView
import com.example.movies.ui.main.search.SearchView
import com.example.movies.ui.main.actor.ActorDetails
import com.example.movies.ui.main.rate.RatedView
import com.example.movies.ui.main.tabs.home.TvDetailsView
import com.example.movies.ui.main.tabs.saved.SavedViewModel
import com.example.movies.ui.main.tv.TvFullView
import com.example.movies.ui.main.watchlist.WatchList
import com.example.movies.ui.theme.AppTypography
import com.example.movies.ui.theme.MoviesTheme
import com.example.splash.SplashView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags("en")
        }

        setContent {
            MoviesTheme {
                App( deepLink = intent?.data)
            }
        }
    }
}

@Composable
fun App(    deepLink: Uri?) {
    val navController = rememberNavController()
    NavHost(
        navController,
        startDestination = AppRoutes.SplashRoute
    ) {
        composable<AppRoutes.RatedRoute> {
            RatedView(navController)
        }
        composable<AppRoutes.WatchListRoute> {
            WatchList(navController)
        }
        composable<AppRoutes.ActorRoute> {
            val data = it.toRoute<AppRoutes.ActorRoute>()
            ActorDetails(
                data.id,
                navController

            )
        }
        composable<AppRoutes.SplashRoute> {
            SplashView(navController)
        }
        composable<AppRoutes.AuthCallbackRoute>(
            deepLinks = listOf(
                navDeepLink<AppRoutes.AuthCallbackRoute>(
                    basePath = "movies://auth"
                )
            )
        ) {

        }
        composable<AppRoutes.CreateSessionRoute> {
            val requestToken = deepLink?.getQueryParameter("request_token")
            val approved = deepLink?.getQueryParameter("approved") == "true"
            CreateSession(navController, deepLink)

        }
        composable<AppRoutes.TvDetailsRoute> {
            val data = it.toRoute<AppRoutes.TvDetailsRoute>()
            TvDetailsView(data.id, data.type, navController)
        }
        composable<AppRoutes.TvFullRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<AppRoutes.TvFullRoute>()
            TvFullView(

                genre = route.genre,
                navController = navController
            )
        }
        composable<AppRoutes.MovieFullRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<AppRoutes.MovieFullRoute>()
            MoviesFullView(
                genre = route.genre,
                navController = navController
            )
        }

        composable<AppRoutes.MainRoute> {
            MainScreen(navController)
        }
        composable<AppRoutes.SearchRoute> {
            SearchView(navController)
        }
    }

}


