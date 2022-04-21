package com.paiva.superfilmes

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers


abstract class BaseRobot {

    fun checkViewIsDisplayed(@IdRes viewId: Int) = apply {
        Espresso.onView(ViewMatchers.withId(viewId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun checkViewIsHidden(@IdRes viewId: Int) = apply {
        Espresso.onView(ViewMatchers.withId(viewId))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    fun checkViewIsEnabled(@IdRes viewId: Int) {
        Espresso.onView(ViewMatchers.withId(viewId))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    fun checkViewHasText(@IdRes viewId: Int, expected: String) {
        Espresso.onView(ViewMatchers.withId(viewId))
            .check(ViewAssertions.matches(ViewMatchers.withText(expected)))
    }

    fun checkHintText(@IdRes viewId: Int, expected: String) {
        Espresso.onView(ViewMatchers.withId(viewId))
            .check(ViewAssertions.matches(ViewMatchers.withHint(expected)))
    }

    fun typeTextInEditText(@IdRes viewId: Int, text: String) = apply {
        Espresso.onView(ViewMatchers.withId(viewId))
            .perform(ViewActions.replaceText(text))
    }

    fun click(@IdRes viewId: Int) = apply {
        Espresso.onView(ViewMatchers.withId(viewId))
            .perform(ViewActions.click())
    }
}