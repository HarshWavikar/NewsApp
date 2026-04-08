package com.harshcode.newsapp.domain.repository

import androidx.paging.PagingData
import com.harshcode.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>
}

/*
Paging is basically a technique that enables you to fetch data by small chunks from the server.
In our news API we probably have millions of articles if we want to fetch all of that together then
that will take an infinite time and the solution here comes with paging.

So we specify how many articles we want to load from the API and then the API will respond to that
helping us to get faster responses.

To implement paging3 library we need to create a remote paging source. In data -> remote -> new class NewsPaging Source
 */