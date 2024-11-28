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

    // Получение всех слов из таблицы word_text
    @Query("SELECT * FROM word_text")
    suspend fun getAllWords(): List<WordTextEntity>

    // Получаем последний wordId из таблицы word_ids
    @Query("SELECT wordId FROM word_ids ORDER BY wordId DESC LIMIT 1")
    suspend fun getLastWordId(): Int?

    // Получаем данные о слове (wordText) по wordId
    @Query("SELECT * FROM word_text WHERE wordId = :wordId")
    suspend fun getWordTextById(wordId: Int): WordTextEntity?

    // Получаем определения по wordId
    @Query("SELECT * FROM word_definition WHERE wordId = :wordId")
    suspend fun getDefinitionsByWordId(wordId: Int): List<WordDefinitionEntity>

    // Получаем переводы определений по wordId
    @Query("SELECT * FROM word_definition_translation WHERE wordId = :wordId")
    suspend fun getDefinitionTranslationsByWordId(wordId: Int): List<WordDefinitionTranslationEntity>

    // Получаем примеры предложений по wordId
    @Query("SELECT * FROM example_sentence WHERE wordId = :wordId")
    suspend fun getExampleSentencesByWordId(wordId: Int): List<ExampleSentenceEntity>

    // Получаем переводы примеров предложений по wordId
    @Query("SELECT * FROM example_sentence_translation WHERE wordId = :wordId")
    suspend fun getExampleSentenceTranslationsByWordId(wordId: Int): List<ExampleSentenceTranslationEntity>

    // Получаем аудио по wordId
    @Query("SELECT * FROM audio_data WHERE wordId = :wordId")
    suspend fun getAudioByWordId(wordId: Int): AudioEntity?

    @Query("SELECT COUNT(*) FROM word_ids")
    suspend fun getStudiedWordsCount(): Int

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