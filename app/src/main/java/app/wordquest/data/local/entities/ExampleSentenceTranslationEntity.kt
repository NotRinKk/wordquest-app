package app.wordquest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "example_sentence_translation")
data class ExampleSentenceTranslationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val wordId: Int,
    val sentenceTranslation: String
)