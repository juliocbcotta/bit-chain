package br.com.bit.chain.components.chart

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import br.com.bit.chain.components.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.LargeValueFormatter


class ChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.chart_view, this, true)
    }

    private val formatter = ChartViewDateFormatter()
    private val title by lazy { findViewById<TextView>(R.id.titleView) }
    private val subtitle by lazy { findViewById<TextView>(R.id.subtitleView) }
    private val cardView by lazy { findViewById<CardView>(R.id.cardview) }
    private val chart by lazy { findViewById<LineChart>(R.id.chart) }

    private fun setTitle(title: String) {
        this.title.text = title
    }

    private fun setSubtitle(subtitle: String) {
        this.subtitle.text = subtitle
    }

    private fun fetchAccentColor(): Int {
        val typedValue = TypedValue()

        val a = context.obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorAccent))
        val color = a.getColor(0, 0)

        a.recycle()

        return color
    }

    private fun setChartData(
        values: List<ChartValueUiModel>,
        valuesDescription: String
    ) {
        val entryValues = values.map { value ->
            Entry(value.x, value.y)
        }
        val dataSet = LineDataSet(entryValues, valuesDescription)
            .apply {
                color = fetchAccentColor()
                setDrawValues(false)
                setDrawCircles(false)
                lineWidth = 2.0f
            }
        val lineData = LineData(dataSet)

        chart.apply {
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
                verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            }
        }
        chart.invalidate()
    }

    fun setUiModel(uiModel: ChartUiModel) {
        cardView.visibility = View.VISIBLE
        setTitle(uiModel.title)
        setSubtitle(uiModel.subtitle)
        val formattedDescription = formatter.format(context, uiModel.start, uiModel.end)
        setChartData(uiModel.values, formattedDescription)
    }


}
