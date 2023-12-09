package ar.edu.unlam.mobile2.domain.repository

import androidx.datastore.preferences.core.Preferences
import ar.edu.unlam.mobile2.domain.model.FavoriteMovieId
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val allFavoriteMoviesIds: Flow<List<FavoriteMovieId>>
    suspend fun getAllFavoriteMoviesQuantity(): Flow<Int>
    suspend fun isMovieFavorite(movieId: Int): Boolean
    suspend fun insertFavoriteMovie(movieId: FavoriteMovieId)
    suspend fun deleteFavoriteMovie(movieId: FavoriteMovieId)
    suspend fun searchFavoriteMovies(searchTerm: String): Flow<List<FavoriteMovieId>>
    suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T>
    suspend fun <T> insertPreference(key: Preferences.Key<T>, value: T)
    suspend fun <T> removePreference(key: Preferences.Key<T>)
    suspend fun clearAllPreference()
}