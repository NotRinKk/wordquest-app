package app.wordquest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_definition_translation")
data class WordDefinitionTranslationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val wordId: Int,
    val definitionTranslation: String
)