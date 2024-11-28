package app.wordquest.domain.model

data class WordData(
    val word: String,
    val translation: String,
    val definitions: List<String>,
    val definitionTranslations: List<String>,
    val sentences: List<String>,
    val sentenceTranslations: List<String>
)
