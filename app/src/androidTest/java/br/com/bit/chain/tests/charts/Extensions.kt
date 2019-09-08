package br.com.bit.chain.tests.charts

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

val chartDataResponseJson = """
                    {
                        "name": "name",
                        "description" : "description",
                        "unit" : "unit",
                        "values" :[
                                {
                                 "x" : 1.0,
                                 "y" : 2.0
                                },
                                 {
                                 "x" : 2.0,
                                 "y" : 4.0
                                },
                                 {
                                 "x" : 3.0,
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
