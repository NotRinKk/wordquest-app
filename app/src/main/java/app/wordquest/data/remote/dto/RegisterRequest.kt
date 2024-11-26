package app.wordquest.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val username: String,
    val password_hash: String,
    val email: String)