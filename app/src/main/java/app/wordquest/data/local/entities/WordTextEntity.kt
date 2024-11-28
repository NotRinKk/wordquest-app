package app.wordquest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_text")
data class WordTextEntity(
    @PrimaryKey val wordId: Int,
    val wordText: String,
    val translation: String
)