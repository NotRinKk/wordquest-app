package app.wordquest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_definition")
data class WordDefinitionEntity(
    @PrimaryKey(autoGenerate = true)  val id: Int = 0,
    val wordId: Int,
    val definition: String
)