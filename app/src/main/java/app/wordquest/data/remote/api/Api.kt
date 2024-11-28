package app.wordquest.data.remote.api

import app.wordquest.data.remote.dto.WordResponse
import io.ktor.client.statement.*

interface ApiService {
    suspend fun login(username: String, password: String): HttpResponse
    suspend fun register(username: String, password: String, email: String): HttpResponse
    suspend fun getNewWord(ids: List<Int>): WordResponse?
}