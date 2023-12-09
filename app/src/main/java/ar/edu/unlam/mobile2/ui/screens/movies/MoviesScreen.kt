package ar.edu.unlam.mobile2.ui.screens.movies

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile2.ui.screens.composables.MoviesGrid
import ar.edu.unlam.mobile2.ui.screens.composables.SearchBar
import ar.edu.unlam.mobile2.domain.model.Movie
import ar.edu.unlam.mobile2.ui.screens.ScreenUiState
import ar.edu.unlam.mobile2.ui.screens.composables.MoviesLists
import kotlinx.coroutines.delay

@Composable
fun MoviesScreen(
    modifier: Modifier = Modifier,
    moviesUiStateList: List<Pair<Int, ScreenUiState<List<Movie>>>>,
    searchedMoviesUiStateList: ScreenUiState<List<Movie>>,
    setSearchedMoviesList: (String) -> Unit,
    navigateToDetails: (Int) -> Unit
) {
    val searchTerm = remember { mutableStateOf("") }

    LaunchedEffect(searchTerm.value) {
        delay(3000)
        setSearchedMoviesList(searchTerm.value)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
    ) {
        SearchBar(searchTerm)
        Spacer(modifier.height(20.dp))
        if (searchTerm.value.isEmpty()) {
            MoviesLists(moviesUiStateList = moviesUiStateList, navigateToDetails = navigateToDetails)
        } else {
            MoviesGrid(searchedMoviesUiStateList, navigateToDetails = navigateToDetails)
        }
    }
}