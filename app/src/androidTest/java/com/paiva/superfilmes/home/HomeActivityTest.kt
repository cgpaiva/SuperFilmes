package com.paiva.superfilmes.home

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.paiva.superfilmes.presentation.topmovies.HomeActivity
import com.paiva.superfilmes.rule.LazyActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    @get:Rule
    val activityTestRule =
        LazyActivityScenarioRule(launchActivity = false, HomeActivity::class.java)

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Test
    fun validateViewsVisibility() {
        activityTestRule.launch()

        HomeActivityRobot()
            .checkViewsVisibility()
            .checkProgressBarsIsGoneAfterApiCall()
            .checkEnabledViews()

    }

    @Test
    fun validateStaticTexts() {
        activityTestRule.launch()

        HomeActivityRobot()
            .checkStaticsTexts()
            .checkHintTexts()

    }

    @Test
    fun validateSearchMovie() {
        activityTestRule.launch()

        HomeActivityRobot()
            .checkSearch()

    }
}