package br.com.bit.chain

import androidx.preference.PreferenceManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.bit.chain.app.BitApplication
import br.com.bit.chain.app.di.DaggerAppComponent
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseTest {
    private val app = InstrumentationRegistry.getInstrumentation()
        .targetContext
        .applicationContext as BitApplication

    protected val server = MockWebServer()

    @Before
    fun setup() {
        server.start()
        val url = server.url("/").toString()
        DaggerAppComponent.factory()
            .create(app, url)
            .inject(app)

        clearCache()
    }

    private fun clearCache() {
        PreferenceManager.getDefaultSharedPreferences(app)
            .edit()
            .clear()
            .apply()
    }

    @After
    open fun tearDown() {
        server.shutdown()
    }
}
