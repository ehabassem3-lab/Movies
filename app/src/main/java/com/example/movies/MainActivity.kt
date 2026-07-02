package com.example.movies

import android.app.LocaleManager
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.SplashScreen
import com.example.movies.ui.main.MainScreen
import com.example.movies.ui.main.search.SearchView
import com.example.movies.ui.main.tv.TvFullView
import com.example.movies.ui.theme.MoviesTheme
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
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController ,
        startDestination = AppRoutes.MainRoute
    ){
        composable <AppRoutes.TvFullRoute>{backStackEntry ->
            val route = backStackEntry.toRoute<AppRoutes.TvFullRoute>()
            TvFullView(
                title =route.header,
                genre = route.genre,
                navController = navController
            )
        }

     composable<AppRoutes.MainRoute>{
         MainScreen(navController)
     }
        composable <AppRoutes.SearchRoute>{
            SearchView(navController)
        }
    }

}

