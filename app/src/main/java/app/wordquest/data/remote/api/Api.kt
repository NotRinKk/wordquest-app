package app.wordquest.data.remote.api

import io.ktor.client.statement.*

interface ApiService {
    suspend fun login(username: String, password: String): HttpResponse
    suspend fun register(username: String, password: String, email: String): HttpResponse
}