package app.wordquest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import app.wordquest.data.local.dao.WordDao
import app.wordquest.data.local.entities.WordIdEntity
import app.wordquest.data.local.entities.*

@Database(entities = [WordIdEntity::class, AudioEntity::class, ExampleSentenceEntity::class,
                      ExampleSentenceTranslationEntity::class, WordDefinitionEntity::class,
                      WordDefinitionTranslationEntity::class, WordTextEntity::class], version = 1,
    exportSchema = false)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}