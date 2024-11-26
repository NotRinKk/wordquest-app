package app.wordquest.data.remote.api

import app.wordquest.BuildConfig
import app.wordquest.data.remote.dto.LoginRequest
import app.wordquest.data.remote.dto.RegisterRequest
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.statement.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val client: HttpClient) : ApiService {

    override suspend fun login(username: String, password: String): HttpResponse {
        val urlLogin = BuildConfig.API_URL_LOGIN
        val request = LoginRequest(username, password)

        return client.post {
            url(urlLogin)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(request))
        }
    }

    override suspend fun register(username: String, password: String, email: String): HttpResponse {
        val urlRegister = BuildConfig.API_URL_REGISTER
        val request = RegisterRequest(username, password, email)

        return client.post {
            url(urlRegister)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            setBody(Json.encodeToString(request))
        }
    }
}