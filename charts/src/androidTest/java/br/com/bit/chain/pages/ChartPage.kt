package br.com.bit.chain.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import br.com.bit.chain.charts.R
import org.hamcrest.CoreMatchers.allOf

class ChartPage {
    fun hasTitle(title: String) {
        onView(withId(R.id.titleView))
            .check(matches(allOf(isDisplayed(), withText(title))))
    }

    fun hasSubtitle(subtitle: String) {
        onView(withId(R.id.subtitleView))
            .check(matches(allOf(isDisplayed(), withText(subtitle))))
    }

    fun hasAlertTitle() {
        onView(withText(R.string.charts_alert_title))
            .check(matches(allOf(isDisplayed())))
    }

    fun hasAlertMessage() {
        onView(withText(R.string.charts_alert_message))
            .check(matches(allOf(isDisplayed())))
    }

    fun tryAgain() {
        onView(withText(R.string.charts_alert_positive_button))
            .perform(click())
    }

    fun closeAlert() {
        onView(withText(R.string.charts_alert_negative_button))
            .perform(click())
    }

    fun pressBack() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack()
    }
}
