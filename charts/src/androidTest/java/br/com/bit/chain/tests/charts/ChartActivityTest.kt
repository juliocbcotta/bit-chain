package br.com.bit.chain.tests.charts

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import br.com.bit.chain.BaseTest
import br.com.bit.chain.charts.presentation.ChartActivity
import br.com.bit.chain.pages.ChartPage
import kotlin.test.assertEquals
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class ChartActivityTest : BaseTest() {

    val calendar = Calendar.getInstance().apply {
        set(Calendar.YEAR, 2019)
        set(Calendar.MONTH, 8)
        set(Calendar.DAY_OF_MONTH, 8)
        set(Calendar.HOUR, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }

    val x = (calendar.time.time / 1000).toFloat()
    val x1 = ((calendar.time.time + TimeUnit.DAYS.toSeconds(1) )/ 1000).toFloat()
    val x2 = ((calendar.time.time + TimeUnit.DAYS.toSeconds(2) )/ 1000).toFloat()


    val chartDataResponseJson = """
                    {
                        "name": "name",
                        "description" : "description",
                        "unit" : "unit",
                        "period": "day",
                        "values" :[
                                {
                                 "x" : $x,
                                 "y" : 2.0
                                },
                                 {
                                 "x" : $x1,
                                 "y" : 4.0
                                },
                                 {
                                 "x" : $x2,
                                 "y" : 6.0
                                },
                                 {
                                 "x" : 4.0,
                                 "y" : 8.0
                                }
                            ]
                    }
            """.trimMargin()
    @get:Rule
    var activityRule: ActivityTestRule<ChartActivity> =
        ActivityTestRule(ChartActivity::class.java, false, false)

    @After
    override fun tearDown() {
        super.tearDown()
        activityRule.finishActivity()
    }

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
    fun should_show_try_again_alert_and_dismiss_on_negative_option() {
        server.enqueue401()

        launch {
            closeAlert()
        }
    }

    @Test
    fun should_show_try_again_alert_and_dismiss_on_back_pressed() {
        server.enqueue401()

        launch {
            pressBack()
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
