package com.harshcode.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.harshcode.newsapp.data.remote.NewsApi
import com.harshcode.newsapp.data.remote.NewsPagingSource
import com.harshcode.newsapp.domain.model.Article
import com.harshcode.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi
): NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return  Pager(
            // Here I have defined the config for the paging to get 10 articles on each request as a response.
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(",")
                )
            }
        ).flow
    }
}