package app.wordquest.data.di

import app.wordquest.data.remote.api.ApiService
import app.wordquest.data.remote.api.ApiServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule  {

    @Provides
    @Singleton
    fun provideHttpClient():HttpClient{
        return HttpClient(Android){
            install(Logging){
                level=LogLevel.ALL
            }
            install(ContentNegotiation){
                json(Json)
            }
        }
    }

    @Provides
    @Singleton
    fun provideApiService(client: HttpClient): ApiService {
        return ApiServiceImpl(client)
    }
}