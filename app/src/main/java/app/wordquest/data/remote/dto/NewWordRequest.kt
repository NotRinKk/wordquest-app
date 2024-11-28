package app.wordquest.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewWordRequest(
    val ids: List<Int>
)
