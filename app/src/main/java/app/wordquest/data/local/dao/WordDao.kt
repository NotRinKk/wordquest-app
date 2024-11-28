package app.wordquest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import app.wordquest.data.local.entities.*

@Dao
interface WordDao {

    // Метод для получения всех wordId
    @Query("SELECT wordId FROM word_ids")
    suspend fun getAllWordIds(): List<Int>

    // Вставка нового слова
    @Insert
    suspend fun insertWord(wordEntity: WordTextEntity)

    // Вставка аудио URL
    @Insert
    suspend fun insertAudio(audioEntity: AudioEntity)

    // Вставка определений
    @Insert
    suspend fun insertDefinition(definitionEntity: WordDefinitionEntity)

    // Вставка переводов определений
    @Insert
    suspend fun insertDefinitionTranslation(definitionTranslationEntity: WordDefinitionTranslationEntity)

    // Вставка примеров
    @Insert
    suspend fun insertExampleSentence(exampleSentenceEntity: ExampleSentenceEntity)

    // Вставка переводов примеров
    @Insert
    suspend fun insertExampleSentenceTranslation(exampleSentenceTranslationEntity: ExampleSentenceTranslationEntity)

    // Вставка только wordId в отдельную таблицу
    @Insert
    suspend fun insertWordId(wordIdEntity: WordIdEntity)

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