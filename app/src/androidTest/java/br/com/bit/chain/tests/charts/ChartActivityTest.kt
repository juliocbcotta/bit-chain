package br.com.bit.chain.tests.charts

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.bit.chain.BaseTest
import br.com.bit.chain.charts.presentation.ChartActivity
import br.com.bit.chain.pages.ChartPage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals


@RunWith(AndroidJUnit4::class)
class ChartActivityTest : BaseTest() {
    @get:Rule
    var activityRule: ActivityTestRule<ChartActivity> =
        ActivityTestRule(ChartActivity::class.java, false, false)


    private fun launch(receiver: ChartPage.() -> Unit) {
        activityRule.launchActivity(Intent())
        receiver(ChartPage())
    }

    @Test
    fun should_load_market_price_chart() {
        server.enqueueBody(chartDataResponseJson)

        launch {}

        val request = server.takeRequest()
        assertEquals(request.path, "/market-price")
    }

    @Test
    fun should_show_card_title_and_subtitle() {
        server.enqueueBody(chartDataResponseJson)

        launch {
            hasTitle("name")
            hasSubtitle("description")
        }

    }

    @Test
    fun should_show_try_again_alert() {
        server.enqueue401()

        launch {
            hasAlertTitle()
            hasAlertMessage()
        }
    }

    @Test
    fun should_show_try_again_alert_and_try_again() {
        server.enqueue401()
        server.enqueueBody(chartDataResponseJson)

        launch {
            tryAgain()
            hasTitle("name")
            hasSubtitle("description")
        }
    }

}