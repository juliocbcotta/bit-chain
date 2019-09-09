package br.com.bit.chain.charts.data.cache

import android.content.SharedPreferences
import br.com.bit.chain.charts.data.cache.models.ChartDataDao
import br.com.bit.chain.charts.di.ChartActivityModule.Companion.NAMED_GSON_FOR_CACHE
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject
import javax.inject.Named

internal interface ChartDataLocalCache {
    fun get(): Maybe<ChartDataDao>
    fun save(chartDataResponse: ChartDataDao): Completable
}

internal class ChartDataLocalCacheImpl @Inject constructor(
    private val prefs: SharedPreferences,
    @Named(NAMED_GSON_FOR_CACHE)
    private val gson: Gson
) : ChartDataLocalCache {

    private val key: String = KEY_CACHE

    override fun get(): Maybe<ChartDataDao> {
        return Maybe.create { emitter ->
            val json = prefs.getString(key, null)
            if (json == null) {
                emitter.onComplete()
            } else {
                val data = gson.fromJson(json, ChartDataDao::class.java)
                emitter.onSuccess(data)
            }
        }
    }

    override fun save(chartDataResponse: ChartDataDao): Completable {
        return Completable.fromAction {
            val json = gson.toJson(chartDataResponse)
            prefs.edit()
                .putString(key, json)
                .apply()
        }
    }

    companion object {
        private const val KEY_CACHE: String = "cache key"
    }
}
