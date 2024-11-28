package app.wordquest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_ids")
data class WordIdEntity(
    @PrimaryKey val wordId: Int
)