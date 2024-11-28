package app.wordquest.data.local.repository

import androidx.room.PrimaryKey
import app.wordquest.data.local.dao.WordDao
import app.wordquest.data.local.entities.*
import app.wordquest.data.remote.api.ApiService
import app.wordquest.data.remote.dto.WordResponse
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val wordDao: WordDao,
    private val apiService: ApiService
) {

    // Получение всех сохраненных wordId из базы данных
    suspend fun getAllWordIds(): List<Int> {
        return wordDao.getAllWordIds()
    }

    // Получение нового слова по списку ids
    suspend fun getNewWord(ids: List<Int>): WordResponse? {
        return apiService.getNewWord(ids)
    }

    // Сохранение данных о новом слове и всех его компонентах
    suspend fun saveWord(wordResponse: WordResponse) {
        // Сохраняем основное слово (wordId и текст)
        wordDao.insertWord(WordTextEntity(wordResponse.wordId, wordResponse.wordText, wordResponse.translation))

        // Сохраняем аудио URL
        wordResponse.audioUrl?.let { audioUrl ->
            wordDao.insertAudio(AudioEntity(wordResponse.wordId, audioUrl))
        }

        // Сохраняем перевод
        wordResponse.definition.forEach { definition ->
            wordDao.insertDefinition(WordDefinitionEntity(
                    wordId = wordResponse.wordId,
                    definition = definition))
        }

        wordResponse.definitionTranslation.forEach { translation ->
            wordDao.insertDefinitionTranslation(WordDefinitionTranslationEntity(
                    wordId = wordResponse.wordId,
                    definitionTranslation = translation))
        }

        wordResponse.exampleSentences.forEach { sentence ->
            wordDao.insertExampleSentence(ExampleSentenceEntity(
                    wordId = wordResponse.wordId,
                    sentence = sentence))
        }

        wordResponse.exampleSentenceTranslations.forEach { sentenceTranslation ->
            wordDao.insertExampleSentenceTranslation(ExampleSentenceTranslationEntity(
                    wordId = wordResponse.wordId,
                    sentenceTranslation = sentenceTranslation))
        }

        // Сохраняем только wordId в отдельной таблице
        wordDao.insertWordId(WordIdEntity(wordResponse.wordId))
    }

    // Функция для очистки всех данных
    suspend fun clearAllData() {
        wordDao.deleteAllWordIds()
        wordDao.deleteAllWordText()
        wordDao.deleteAllWordDefinitions()
        wordDao.deleteAllWordDefinitionTranslations()
        wordDao.deleteAllExampleSentences()
        wordDao.deleteAllExampleSentenceTranslations()
        wordDao.deleteAllAudioData()
    }
}