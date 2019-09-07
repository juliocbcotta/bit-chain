package br.com.bit.chain.tests.charts

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer


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