package app.wordquest.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password_hash: String
)
