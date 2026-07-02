package com.example.movies.ui.main.tabs.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movies.network.response.discover.DiscoverItem
import com.example.movies.network.response.discover.MoviesItem
import com.example.movies.routes.AppRoutes
import com.example.movies.ui.main.search.MovieItem
import com.example.movies.ui.theme.AppTypography

@Composable
fun TvSection(
    title: String,
    genre : Int? ,
    tvList: List<DiscoverItem?>? = null,
    movies : List<MoviesItem?>? = null ,
    navController: NavController,
    onViewAll: () -> Unit
) {
    val  colorScheme = MaterialTheme.colorScheme

    Column (
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth(.95f)
            .height(400.dp) ,
        verticalArrangement = Arrangement.Center ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.SpaceAround ,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                title ,
                modifier = Modifier.padding(vertical = 10.dp),
                textAlign = TextAlign.Start,
                style = AppTypography.titleLarge.copy
                    (
                    color = colorScheme.onBackground ,
                    fontWeight = FontWeight.Bold ,
                    fontSize = 24.sp

                )
            )
            Text("")
            Text("")

            Text(
                "View ALL ",
                textAlign = TextAlign.End ,
                style = AppTypography.titleLarge.copy
                    (
                    color = colorScheme.onBackground ,
                    fontWeight = FontWeight.Light ,
                    fontSize = 12.sp

                ) ,
                modifier = Modifier.clickable{
                    onViewAll()
                }




            )


        }
        LazyRow(
            modifier = Modifier.fillMaxSize() ,
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp) ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (tvList == null){
                items(movies!!){
                    MovieItem( movieItem = it) {

                    }
                }
            }else{
                items(tvList){
                    MovieItem(tvItem =  it) {

                    }
                }
            }


            item{
                Box(
                    modifier =
                        Modifier
                            .size(40.dp)
                            .background(colorScheme.onBackground, shape = CircleShape)
                            .clickable{
                                navController.navigate(AppRoutes.TvFullRoute(title, genre))
                            } ,
                    contentAlignment = Alignment.Center
                ){
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward ,
                        contentDescription = "" ,
                        tint = colorScheme.background ,
                        modifier = Modifier.size(20.dp) ,


                        )
                }
            }

        }

    }
}






