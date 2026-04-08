package com.harshcode.newsapp.presentation.navGraph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.harshcode.newsapp.presentation.common.ArticleCardShimmerEffect
import com.harshcode.newsapp.presentation.home.HomeScreen
import com.harshcode.newsapp.presentation.home.HomeViewModel
import com.harshcode.newsapp.presentation.onBoarding.OnBoardingScreen
import com.harshcode.newsapp.presentation.onBoarding.OnBoardingViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ){
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(
                route = Route.OnBoardingScreen.route
            ){
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ){
            composable(
                route = Route.NewsNavigatorScreen.route
            ){
                val viewModel : HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles =articles,
                    navigate = {}
                )
            }
        }
    }
}