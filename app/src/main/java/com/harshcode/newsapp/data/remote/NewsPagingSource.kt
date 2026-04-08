package com.harshcode.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.harshcode.newsapp.domain.model.Article
import  kotlin.Exception

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val sources: String
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        // here I have made a request to the API and return that articles
        val page = params.key ?: 1
        return try {
            val newsResponse = newsApi.getNews(page = page, sources = sources)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title } // remove duplicates
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e,
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        /* the state parameter has info about the current state of our paging
            The anchorPosition is the latest accessed page in the list and if that's not null the we get the closest page to that position.
            Then we can use the anchorPage to get the key/page.
            Since we have current page so- prevKey + 1 so this will give us the current page else
         */
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition = anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}