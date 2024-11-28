package app.wordquest.data.local.repository

import androidx.room.PrimaryKey
import app.wordquest.data.local.dao.WordDao
import app.wordquest.data.local.entities.*
import app.wordquest.data.remote.api.ApiService
import app.wordquest.data.remote.dto.WordResponse
import app.wordquest.domain.model.WordData
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

    // Получаем последний wordId из таблицы word_ids
    suspend fun getLastWordId(): Int? {
        return wordDao.getLastWordId()
    }

    // Получаем все данные, связанные с последним wordId
    suspend fun getWordDataById(wordId: Int): WordData? {
        // Получаем данные для этого wordId из всех таблиц
        val wordText = wordDao.getWordTextById(wordId)
        val definitions = wordDao.getDefinitionsByWordId(wordId)
        val translations = wordDao.getDefinitionTranslationsByWordId(wordId)
        val exampleSentences = wordDao.getExampleSentencesByWordId(wordId)
        val exampleSentenceTranslations = wordDao.getExampleSentenceTranslationsByWordId(wordId)
        //val audio = wordDao.getAudioByWordId(wordId)

        return wordText?.let {
            WordData(
                word = it.wordText,
                translation = it.translation,
                definitions = definitions.map { definition -> definition.definition },
                definitionTranslations = translations.map { translation -> translation.definitionTranslation },
                sentences = exampleSentences.map { sentence -> sentence.sentence },
                sentenceTranslations = exampleSentenceTranslations.map { sentence -> sentence.sentenceTranslation }
            )
        }
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