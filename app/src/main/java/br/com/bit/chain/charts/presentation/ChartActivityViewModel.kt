package br.com.bit.chain.charts.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.bit.chain.charts.data.models.ChartData
import br.com.bit.chain.charts.data.models.ChartDataValue
import br.com.bit.chain.charts.data.repository.ChartRepository
import br.com.bit.chain.components.chart.ChartUiModel
import br.com.bit.chain.components.chart.ChartUiValueModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

class ChartActivityViewModel @Inject constructor(
    @Named("MainScheduler")
    private val mainScheduler: Scheduler,
    private val repository: ChartRepository
) : ViewModel() {

    val onData = MutableLiveData<ChartUiModel>()

    private val disposables = CompositeDisposable()

    init {
        loadChart()
    }

    private fun loadChart() {
        disposables.add(repository.getChartData()
            .observeOn(mainScheduler)
            .map {
                it.toChartUiModel()
            }
            .subscribe({ uiModel ->
                onData.value = uiModel

            }, {
                it.printStackTrace()
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
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