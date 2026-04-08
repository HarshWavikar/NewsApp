package com.harshcode.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.harshcode.newsapp.domain.model.Article
import com.harshcode.newsapp.presentation.Dimens.MediumPadding1
import com.harshcode.newsapp.presentation.common.ArticlesList
import com.harshcode.newsapp.presentation.common.SearchBar
import com.harshcode.newsapp.presentation.navGraph.Route

@Composable
fun SearchScreen(
    searchState: SearchState,
    event: (SearchEvent) -> Unit,
    navigate: (String) -> Unit,
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            modifier = Modifier,
            text = searchState.searchQuery,
            readOnly = false,
            onValueChange = {
                event(SearchEvent.UpdateSearchQuery(it))
            },
            onSearch = {event(SearchEvent.SearchNews)}
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        searchState.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onClick = {navigate(Route.DetailsScreen.route)})
        }
    }
}