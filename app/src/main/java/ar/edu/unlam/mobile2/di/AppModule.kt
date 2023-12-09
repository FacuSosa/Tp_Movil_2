package ar.edu.unlam.mobile2.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import ar.edu.unlam.mobile2.data.local.database.UserDatabase
import ar.edu.unlam.mobile2.data.network.ApiKeyInterceptor
import ar.edu.unlam.mobile2.data.network.MoviesApiService
import ar.edu.unlam.mobile2.data.repository.LocalUserRepository
import ar.edu.unlam.mobile2.data.repository.NetworkMoviesRepository
import ar.edu.unlam.mobile2.domain.repository.MoviesRepository
import ar.edu.unlam.mobile2.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val Context.settingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    @Provides
    @Singleton
    fun provideMoviesApiService(): MoviesApiService {
        val baseUrl = "https://api.themoviedb.org/3/"
        val apiKey = "e35c2104fffb88f5f3b425f6024d334f"
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor(apiKey))
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(moviesApiService: MoviesApiService): MoviesRepository =
        NetworkMoviesRepository(moviesApiService)

    @Provides
    @Singleton
    fun provideUserRepository(context: Application): UserRepository = LocalUserRepository(
        UserDatabase.getDatabase(context).userDao(),
        context.settingsDataStore
    )
}