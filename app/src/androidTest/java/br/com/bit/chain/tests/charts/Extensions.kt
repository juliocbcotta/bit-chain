package br.com.bit.chain.tests.charts

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.util.*
import java.util.concurrent.TimeUnit

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

fun MockWebServer.enqueueBody(body: String) {
    enqueue(
        MockResponse().setBody(body)
    )
}

fun MockWebServer.enqueue401() {
    enqueue(
        MockResponse().apply {
            status = "HTTP/1.1 401 UNAUTHORIZED"
        }
    )
}
