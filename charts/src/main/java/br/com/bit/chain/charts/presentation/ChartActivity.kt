package br.com.bit.chain.charts.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import br.com.bit.chain.charts.R
import br.com.bit.chain.charts.app.ChartsApp
import br.com.bit.chain.components.chart.ChartView
import dagger.android.AndroidInjection
import javax.inject.Inject

class ChartActivity : AppCompatActivity() {

    private lateinit var viewModel: ChartActivityViewModel

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        ChartsApp.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        viewModel = ViewModelProviders.of(this, factory).get(ChartActivityViewModel::class.java)

        val chartView = findViewById<ChartView>(R.id.chartView)
        val loading = findViewById<View>(R.id.loading)
        viewModel.state.observe(this, Observer<State> { state ->
            when (state) {
                State.Loading -> {
                    loading.visibility = View.VISIBLE
                }
                is State.Success -> {
                    loading.visibility = View.GONE
                    chartView.setUiModel(state.uiModel)
                }
                State.Error -> {
                    loading.visibility = View.GONE
                    showRetryAlert()
                }
                State.End -> finish()
            }
        })
    }

    private fun showRetryAlert() {
        var usedActionToDismissAlert = false

        AlertDialog.Builder(this)
            .setTitle(R.string.charts_alert_title)
            .setMessage(R.string.charts_alert_message)
            .setPositiveButton(R.string.charts_alert_positive_button) { _, _ ->
                usedActionToDismissAlert = true
                viewModel.onAction(
                    Action.TryAgain
                )
            }
            .setNegativeButton(R.string.charts_alert_negative_button) { _, _ ->
                usedActionToDismissAlert = true
                viewModel.onAction(Action.Exit)
            }
            .setOnDismissListener {
                if (!usedActionToDismissAlert) {
                    viewModel.onAction(Action.Exit)
                }
            }
            .create()
            .show()
    }

}

