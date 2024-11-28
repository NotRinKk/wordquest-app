package app.wordquest.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class WordResponse(
    val wordId: Int,
    val wordText: String,
    val audioUrl: String?,
    val translation: String,
    val definition: List<String>,
    val definitionTranslation: List<String>,
    val exampleSentences: List<String>,
    val exampleSentenceTranslations: List<String>
)
