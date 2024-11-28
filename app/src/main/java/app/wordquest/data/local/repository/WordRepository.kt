package app.wordquest.data.local.repository

import app.wordquest.data.local.dao.WordDao
import app.wordquest.data.local.entities.*
import app.wordquest.data.remote.api.ApiService
import app.wordquest.data.remote.dto.WordResponse
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val wordDao: WordDao,
    private val apiService: ApiService
) {

    suspend fun getNewWord(ids: List<Int>): WordResponse? {
        val word = apiService.getNewWord(ids)
        word?.let {
            // Сохраняем WordId
            wordDao.insertWordId(WordIdEntity(it.wordId))

            // Сохраняем текст слова и перевод
            wordDao.insertWordText(
                WordTextEntity(
                    wordId = it.wordId,
                    wordText = it.wordText,
                    translation = it.translation
                )
            )

            // Вставляем определения
            it.definition.forEachIndexed { index, definition ->
                wordDao.insertWordDefinition(
                    WordDefinitionEntity(
                        wordId = it.wordId,
                        definition = definition
                    )
                )
            }

            // Вставляем переводы определений
            it.definitionTranslation.forEachIndexed { index, definitionTranslation ->
                wordDao.insertWordDefinitionTranslation(
                    WordDefinitionTranslationEntity(
                        wordId = it.wordId,
                        definitionTranslation = definitionTranslation
                    )
                )
            }

            // Вставляем примеры предложений
            it.exampleSentences.forEachIndexed { index, sentence ->
                wordDao.insertExampleSentence(
                    ExampleSentenceEntity(
                        wordId = it.wordId,
                        sentence = sentence
                    )
                )
            }

            // Вставляем переводы примеров предложений
            it.exampleSentenceTranslations.forEachIndexed { index, sentenceTranslation ->
                wordDao.insertExampleSentenceTranslation(
                    ExampleSentenceTranslationEntity(
                        wordId = it.wordId,
                        sentenceTranslation = sentenceTranslation
                    )
                )
            }

            // Вставляем аудио
            it.audioUrl?.let { audioUrl ->
                wordDao.insertAudioData(AudioEntity(it.wordId, audioUrl))
            }
        }
        return word
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