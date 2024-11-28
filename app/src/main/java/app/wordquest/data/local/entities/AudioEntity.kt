package app.wordquest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audio_data")
data class AudioEntity(
    @PrimaryKey val wordId: Int,
    val audioUrl: String
)