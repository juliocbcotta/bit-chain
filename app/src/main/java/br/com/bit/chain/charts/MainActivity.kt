package br.com.bit.chain.charts

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.bit.chain.R
import br.com.bit.chain.charts.data.models.ChartData
import br.com.bit.chain.charts.data.repository.ChartRepository
import com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.LEFT
import com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.BOTTOM
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.LargeValueFormatter
import dagger.android.AndroidInjection
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: ChartRepository

    @field:[Inject Named("MainScheduler")]
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

        val entryValues = chartData.values.map { value ->
            Entry(value.x, value.y)
        }
        val dataSet = LineDataSet(entryValues, "")
            .apply {
                setDrawValues(false)
                setDrawCircles(false)
                lineWidth = 2.0f
            }
        val lineData = LineData(dataSet)

        chart.apply {
            visibility = View.VISIBLE
            setPinchZoom(false)
            setTouchEnabled(false)
            description.isEnabled = false
            axisRight.isEnabled = false
            xAxis.isEnabled = false

            setDrawBorders(false)
            setDrawGridBackground(false)

            axisLeft.apply {
                setDrawZeroLine(false)
                valueFormatter = LargeValueFormatter()
            }
            data = lineData

            legend.apply {
                setDrawInside(false)
                verticalAlignment = BOTTOM
                horizontalAlignment = LEFT
            }
        }
        chart.invalidate()

    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
