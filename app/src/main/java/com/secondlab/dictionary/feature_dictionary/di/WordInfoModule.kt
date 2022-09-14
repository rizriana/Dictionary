package com.secondlab.dictionary.feature_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.secondlab.dictionary.feature_dictionary.data.local.Converters
import com.secondlab.dictionary.feature_dictionary.data.local.WordInfoDatabase
import com.secondlab.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.secondlab.dictionary.feature_dictionary.data.remote.DictionaryApi.Companion.BASE_URL
import com.secondlab.dictionary.feature_dictionary.data.repository.WordInfoRepositoryImpl
import com.secondlab.dictionary.feature_dictionary.data.util.GsonParser
import com.secondlab.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import com.secondlab.dictionary.feature_dictionary.domain.use_case.GetWordInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun provideGetWordInfo(repository: WordInfoRepository): GetWordInfoUseCase {
        return GetWordInfoUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        api: DictionaryApi,
        db: WordInfoDatabase,
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(
            api = api,
            dao = db.dao
        )
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            "word.db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }
}