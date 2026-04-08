package com.harshcode.newsapp.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.harshcode.newsapp.R
import com.harshcode.newsapp.domain.model.Article
import com.harshcode.newsapp.presentation.Dimens.MediumPadding1
import com.harshcode.newsapp.presentation.common.ArticlesList
import com.harshcode.newsapp.presentation.common.SearchBar
import com.harshcode.newsapp.presentation.navGraph.Route

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    articles : LazyPagingItems<Article>,
    navigate: (String) -> Unit
) {

    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10){
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))  // Now we will get only 10 articles
                    .joinToString(separator = "\uD83d\uDFE5"){article -> article.title.toString() }
            }else{
                ""
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = MediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = "Application Logo",
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MediumPadding1)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        SearchBar(
            modifier = Modifier
                .padding(MediumPadding1),
            text = "",
            readOnly = true,
            onClick = {
                navigate(Route.SearchScreen.route)
            },
            onValueChange = {  },
            onSearch = {}
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(MediumPadding1)
                .basicMarquee(),
            fontSize = 14.sp,
            color = colorResource(R.color.placeholder)
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            articles = articles,
            onClick = {navigate(Route.DetailsScreen.route)}
        )
    }
}