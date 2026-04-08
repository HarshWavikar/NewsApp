package com.harshcode.newsapp.di

import android.app.Application
import com.harshcode.newsapp.data.manager.LocalUserManagerImpl
import com.harshcode.newsapp.data.remote.NewsApi
import com.harshcode.newsapp.data.repository.NewsRepositoryImpl
import com.harshcode.newsapp.domain.manager.LocalUserManager
import com.harshcode.newsapp.domain.repository.NewsRepository
import com.harshcode.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.harshcode.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.harshcode.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.harshcode.newsapp.domain.usecases.news.GetNews
import com.harshcode.newsapp.domain.usecases.news.NewsUseCases
import com.harshcode.newsapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository{
        return NewsRepositoryImpl(
            newsApi = newsApi
        )
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ) = NewsUseCases(
        getNews = GetNews(newsRepository = newsRepository)
    )
}


