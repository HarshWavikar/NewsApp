package com.harshcode.newsapp.presentation.onBoarding

/*This class will have the events that will be send from UI to the viewModel.
Here we will only have one event */

sealed class OnBoardingEvent {
    object SaveAppEntry: OnBoardingEvent()
}