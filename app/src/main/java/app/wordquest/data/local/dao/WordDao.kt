package app.wordquest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.wordquest.data.local.entities.*

@Dao
interface WordDao {

    @Insert
    suspend fun insertWordId(wordIdEntity: WordIdEntity)

    @Insert
    suspend fun insertWordText(wordTextEntity: WordTextEntity)

    @Insert
    suspend fun insertWordDefinition(wordDefinitionEntity: WordDefinitionEntity)

    @Insert
    suspend fun insertWordDefinitionTranslation(wordDefinitionTranslationEntity: WordDefinitionTranslationEntity)

    @Insert
    suspend fun insertExampleSentence(exampleSentenceEntity: ExampleSentenceEntity)

    @Insert
    suspend fun insertExampleSentenceTranslation(exampleSentenceTranslationEntity: ExampleSentenceTranslationEntity)

    @Insert
    suspend fun insertAudioData(audioEntity: AudioEntity)

    // Очистить все данные
    @Query("DELETE FROM word_ids")
    suspend fun deleteAllWordIds()

    @Query("DELETE FROM word_text")
    suspend fun deleteAllWordText()

    @Query("DELETE FROM word_definition")
    suspend fun deleteAllWordDefinitions()

    @Query("DELETE FROM word_definition_translation")
    suspend fun deleteAllWordDefinitionTranslations()

    @Query("DELETE FROM example_sentence")
    suspend fun deleteAllExampleSentences()

    @Query("DELETE FROM example_sentence_translation")
    suspend fun deleteAllExampleSentenceTranslations()

    @Query("DELETE FROM audio_data")
    suspend fun deleteAllAudioData()
}