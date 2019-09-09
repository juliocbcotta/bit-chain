package br.com.bit.chain.components.chart

import android.content.Context
import br.com.bit.chain.components.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ChartViewDateFormatter(private val dateFormatter: DateFormat = SimpleDateFormat.getDateInstance()) {


    fun format(context: Context, start: Date, end: Date): String {
        val formattedStart = dateFormatter.format(start)
        val formattedEnd = dateFormatter.format(end)
        return context.getString(R.string.chart_range, formattedStart, formattedEnd)
    }
}