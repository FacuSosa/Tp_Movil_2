package ar.edu.unlam.mobile2.ui.screens.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import ar.edu.unlam.mobile2.domain.model.Movie
import ar.edu.unlam.mobile2.ui.screens.shimmerEffect
import ar.edu.unlam.mobile2.ui.theme.MyLightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier = Modifier,
    navigateToDetails: (Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        onClick = { navigateToDetails(movie.id) },
        modifier = modifier
            .height(230.dp)
            .width(170.dp)
    ) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            MoviePosterImage(
                posterPath = movie.posterPath,
                gradientColor = MaterialTheme.colorScheme.surface
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(15.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.TopStart
                ) {
                    CircularProgressBar(
                        percentage = movie.voteAverage,
                        radius = 20.dp,
                        fontSize = 15.sp
                    )
                }
                movie.title?.let {
                    Text(
                        text = it,
                        modifier = modifier.padding(bottom = 24.dp),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.titleLarge.copy(
                            shadow = Shadow(
                                Color.Black,
                                blurRadius = 8f,
                                offset = Offset(4f, 4f)
                            )
                        )
                    )
                }
                movie.releaseDate?.let {
                    Text(
                        text = it,
                        color = MyLightGray,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            shadow = Shadow(
                                Color.Black, blurRadius = 8f,
                                offset = Offset(4f, 4f)
                            )
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingMovieCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = modifier.height(230.dp).width(170.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .shimmerEffect()
        )
    }
}

@Composable
fun ErrorMovieCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        modifier = modifier.height(230.dp).width(170.dp)
    ) {
        Box(modifier = modifier.fillMaxSize()) {
            Box(
                modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.surface)
            )
            ErrorMessage(
                iconSize = 50.dp,
                textSize = 18.sp,
                errorColor = MaterialTheme.colorScheme.background,
                alpha = 1f
            )
        }
    }
}