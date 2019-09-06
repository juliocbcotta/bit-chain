package br.com.bit.chain.charts.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.bit.chain.R
import br.com.bit.chain.components.chart.ChartUiModel
import br.com.bit.chain.components.chart.ChartView
import dagger.android.AndroidInjection
import javax.inject.Inject

class ChartActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        val viewModel = ViewModelProviders.of(this, factory).get(ChartActivityViewModel::class.java)
        val chartView = findViewById<ChartView>(R.id.chartView)

        viewModel.onData.observe(this,
            Observer<ChartUiModel> { uiModel ->
                chartView.setUiModel(uiModel)
            })
    }

}

