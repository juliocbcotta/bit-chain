package br.com.bit.chain.charts.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.bit.chain.R
import br.com.bit.chain.charts.data.models.ChartData
import br.com.bit.chain.charts.data.models.ChartDataValue
import br.com.bit.chain.charts.data.repository.ChartRepository
import br.com.bit.chain.components.chart.ChartUiModel
import br.com.bit.chain.components.chart.ChartUiValueModel
import br.com.bit.chain.components.chart.ChartView
import dagger.android.AndroidInjection
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class ChartActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: ChartRepository

    @field:[Inject Named("MainScheduler")]
    lateinit var mainScheduler: Scheduler

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        val chartView = findViewById<ChartView>(R.id.chartView)
        disposables.add(repository.getChartData()
            .observeOn(mainScheduler)
            .map {
                it.toChartUiModel()
            }
            .subscribe({ uiModel ->
                chartView.setUiModel(uiModel)

            }, {
                it.printStackTrace()
            })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }

    private fun ChartData.toChartUiModel(): ChartUiModel {
        return ChartUiModel(
            title = name,
            subtitle = description,
            values = values.map {
                it.toChartValueUiModel()
            }
        )
    }

    private fun ChartDataValue.toChartValueUiModel(): ChartUiValueModel {
        return ChartUiValueModel(
            x = x,
            y = y
        )
    }

}

