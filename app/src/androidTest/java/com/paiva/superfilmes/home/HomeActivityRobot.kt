package com.paiva.superfilmes.home

import com.paiva.superfilmes.BaseRobot
import com.paiva.superfilmes.R

class HomeActivityRobot: BaseRobot() {

    fun checkViewsVisibility() = apply {
        checkViewIsDisplayed(R.id.textViewTitle)
        checkViewIsDisplayed(R.id.card_view_search_movie)
        checkViewIsDisplayed(R.id.edit_text_search_movie)
        Thread.sleep(5000)
        checkViewIsDisplayed(R.id.recycler_view_top_movies)
    }

    fun checkProgressBarsIsGoneAfterApiCall() = apply {
        checkViewIsHidden(R.id.progress_bar_search_movie)
        checkViewIsHidden(R.id.progress_bar_top_movies)
    }

    fun checkEnabledViews() = apply {
        checkViewIsEnabled(R.id.edit_text_search_movie)
        checkViewIsEnabled(R.id.card_view_search_movie)
    }

    fun checkStaticsTexts() = apply {
        checkViewHasText(R.id.textViewTitle, "Super Filmes")
        checkViewHasText(R.id.textViewTitleTop250, "Top 250")
    }

    fun checkHintTexts() = apply {
        checkHintText(R.id.edit_text_search_movie, "Buscar filmes")
    }

    fun checkSearch() = apply {
        typeTextInEditText(R.id.edit_text_search_movie, "Spider Man")
        click(R.id.card_view_search_movie)
        Thread.sleep(5000)
        checkViewIsDisplayed(R.id.recycler_view_search_movies)
    }
}