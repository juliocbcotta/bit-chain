package br.com.bit.chain.charts.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.bit.chain.charts.data.repository.ChartRepository
import br.com.bit.chain.charts.chartData
import br.com.bit.chain.charts.chartUiModel
import br.com.bit.chain.components.chart.ChartUiModel
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ChartActivityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ChartRepository
    @Mock
    lateinit var onData: Observer<ChartUiModel>

    private val mainScheduler = Schedulers.trampoline()

    @Test
    fun shouldNotifyAChartUiModel() {
        given { repository.getChartData() }.willReturn(Single.just(chartData))

        val viewModel = ChartActivityViewModel(mainScheduler, repository)
        viewModel.onData.observeForever(onData)

        verify(onData).onChanged(chartUiModel)
        verify(repository).getChartData()

        verifyNoMoreInteractions(onData, repository)
    }
}