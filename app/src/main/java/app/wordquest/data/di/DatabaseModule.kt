package app.wordquest.data.di

import android.content.Context
import androidx.room.Room
import app.wordquest.data.local.dao.WordDao
import app.wordquest.data.local.database.WordDatabase
import app.wordquest.data.local.repository.WordRepository
import app.wordquest.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideWordDatabase(@ApplicationContext context: Context): WordDatabase {
        return Room.databaseBuilder(
            context,
            WordDatabase::class.java,
            "word_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideWordDao(wordDatabase: WordDatabase): WordDao {
        return wordDatabase.wordDao()
    }

    @Provides
    @Singleton
    fun provideWordRepository(wordDao: WordDao, apiService: ApiService): WordRepository {
        return WordRepository(wordDao, apiService)
    }
}