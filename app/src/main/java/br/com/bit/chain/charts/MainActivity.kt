package br.com.bit.chain.charts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.bit.chain.R
import br.com.bit.chain.charts.data.models.ChartData
import br.com.bit.chain.charts.data.repository.ChartRepository
import dagger.android.AndroidInjection
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: ChartRepository

    @Inject
    @Named("MainScheduler")
    lateinit var mainScheduler: Scheduler

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        disposables.add(
            repository.getChartData()
                .observeOn(mainScheduler)
                .subscribe({ chart ->
                    populateChartView(chart)

                }, {
                    it.printStackTrace()
                })
        )
    }

    private fun populateChartView(chartData: ChartData) {

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
