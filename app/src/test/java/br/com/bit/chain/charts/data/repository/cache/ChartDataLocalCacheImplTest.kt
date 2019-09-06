package br.com.bit.chain.charts.data.repository.cache

import android.content.SharedPreferences
import br.com.bit.chain.charts.chartDataResponse
import br.com.bit.chain.charts.data.repository.models.ChartDataResponse
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChartDataLocalCacheImplTest {

    @Mock
    lateinit var prefs: SharedPreferences

    @Mock
    lateinit var gson: Gson

    @Mock
    lateinit var editor: SharedPreferences.Editor

    @InjectMocks
    lateinit var cache: ChartDataLocalCacheImpl

    @Test
    fun `should save ChartDataResponse in SharedPreferences`() {

        given { prefs.edit() }
            .willReturn(editor)
        given { editor.putString(anyString(), anyString()) }
            .willReturn(editor)
        given { gson.toJson(any<ChartDataResponse>()) }
            .willReturn("{}")

        cache.save(chartDataResponse)
            .test()
            .assertComplete()

        verify(editor).putString(any(), any())
        verify(editor).apply()
    }

    @Test
    fun `should complete with values`() {
        given { prefs.getString(any(), anyOrNull()) }
            .willReturn(null)

        cache.get()
            .test()
            .assertNoValues()
            .assertComplete()

        verify(gson, never()).fromJson(anyString(), any<Class<ChartDataResponse>>())
    }

    @Test
    fun `should emit cache value before completing`() {
        val json = "just a non null string"

        given { prefs.getString(any(), anyOrNull()) }
            .willReturn(json)

        given { gson.fromJson(json, ChartDataResponse::class.java) }
            .willReturn(chartDataResponse)

        cache.get()
            .test()
            .assertValue(chartDataResponse)
            .assertComplete()

        verify(gson).fromJson(json, ChartDataResponse::class.java)
    }
}