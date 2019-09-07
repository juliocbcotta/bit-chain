package br.com.bit.chain.charts.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import br.com.bit.chain.charts.chartData
import br.com.bit.chain.charts.chartUiModel
import br.com.bit.chain.charts.domain.ChartRepository
import br.com.bit.chain.charts.utils.callOnCleared
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class ChartActivityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var realState: MutableLiveData<State>

    @Mock
    lateinit var repository: ChartRepository

    @Mock
    lateinit var disposable: CompositeDisposable

    private val mainScheduler = Schedulers.trampoline()
    private val ioScheduler = Schedulers.trampoline()

    @Test
    fun `should fetch chart data with success`() {
        given { repository.getChartData() }
            .willReturn(Observable.just(chartData))

        createViewModel()

        verify(realState).value = State.Loading
        verify(realState).value = State.Success(chartUiModel)

        verifyNoMoreInteractions(realState)
    }

    @Test
    fun `should fail to fetch chart data`() {
        val exception = IOException()
        given { repository.getChartData() }
            .willReturn(Observable.error(exception))

        createViewModel()

        verify(realState).value = State.Loading
        verify(realState).value = State.Error

        verifyNoMoreInteractions(realState)
    }

    @Test
    fun `should fail to fetch chart data and try again`() {

        val exception = IOException()
        given { repository.getChartData() }
            .willReturn(Observable.error(exception))

        val viewModel = createViewModel()


        verify(realState).value = State.Loading
        verify(realState).value = State.Error
        verify(repository).getChartData()

        given { repository.getChartData() }
            .willReturn(Observable.just(chartData))

        viewModel.onAction(Action.TryAgain)

        verify(realState, times(2)).value = State.Loading
        verify(realState).value = State.Success(chartUiModel)

        verifyNoMoreInteractions(realState)
    }

    @Test
    fun `should clear disposable on onClear`() {
        val exception = IOException()
        given { repository.getChartData() }
            .willReturn(Observable.error(exception))
        val viewModel = createViewModel()

        viewModel.callOnCleared()

        verify(disposable).clear()
    }

    @Test
    fun `given Exit action state should be End`() {
        given { repository.getChartData() }
            .willReturn(Observable.just(chartData))

        val viewModel = createViewModel()

        viewModel.onAction(Action.Exit)

        verify(realState).value = State.End
    }


    private fun createViewModel(): ChartActivityViewModel {
        return ChartActivityViewModel(
            realState = realState,
            disposables = disposable,
            mainScheduler = mainScheduler,
            ioScheduler = ioScheduler,
            repository = repository
        )
    }


}