package br.com.bit.chain.charts.data.cache

import android.content.SharedPreferences
import br.com.bit.chain.charts.chartDataDao
import br.com.bit.chain.charts.data.cache.models.ChartDataDao
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class ChartDataLocalCacheImplTest {

    @Mock
    lateinit var prefs: SharedPreferences

    @Mock
    lateinit var gson: Gson

    @Mock
    lateinit var editor: SharedPreferences.Editor

    @InjectMocks
    lateinit var cache: ChartDataLocalCacheImpl

    @Test
    fun `should save ChartDataDao in SharedPreferences`() {

        given { prefs.edit() }
            .willReturn(editor)

        given { editor.putString(anyString(), anyString()) }
            .willReturn(editor)

        given { gson.toJson(any<ChartDataDao>()) }
            .willReturn("{}")

        cache.save(chartDataDao)
            .test()
            .assertComplete()

        verify(editor).putString(any(), any())
        verify(editor).apply()
    }

    @Test
    fun `should complete without emitting values`() {
        given { prefs.getString(any(), anyOrNull()) }
            .willReturn(null)

        cache.get()
            .test()
            .assertNoValues()
            .assertComplete()

        verify(gson, never()).fromJson(anyString(), any<Class<ChartDataDao>>())
    }

    @Test
    fun `should emit cache value before completing`() {
        val json = "just a non null string"

        given { prefs.getString(any(), anyOrNull()) }
            .willReturn(json)

        given { gson.fromJson(json, ChartDataDao::class.java) }
            .willReturn(chartDataDao)

        cache.get()
            .test()
            .assertValue(chartDataDao)
            .assertComplete()

        verify(gson).fromJson(json, ChartDataDao::class.java)
    }
}
