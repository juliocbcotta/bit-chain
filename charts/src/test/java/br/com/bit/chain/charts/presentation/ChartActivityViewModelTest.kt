package br.com.bit.chain.charts.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.com.bit.chain.charts.chartData
import br.com.bit.chain.charts.chartUiModel
import br.com.bit.chain.charts.domain.ChartRepository
import br.com.bit.chain.charts.utils.callOnCleared
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
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
internal class ChartActivityViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ChartRepository

    @Mock
    lateinit var disposable: CompositeDisposable

    @Mock
    lateinit var observer: Observer<State>

    private val mainScheduler = Schedulers.trampoline()
    private val ioScheduler = Schedulers.trampoline()

    @Test
    fun `should fetch chart data with success`() {
        given { repository.getChartData() }
            .willReturn(Observable.just(chartData))

        val vm = createViewModel()

        vm.state.observeForever(observer)
        vm.onAction(Action.Load)

        verify(observer).onChanged(State.Loading)
        verify(observer).onChanged(State.Success(chartUiModel))
    }

    @Test
    fun `should fail to fetch chart data`() {
        val exception = IOException()
        given { repository.getChartData() }
            .willReturn(Observable.error(exception))

        val vm = createViewModel()

        vm.state.observeForever(observer)
        vm.onAction(Action.Load)

        verify(observer).onChanged(State.Loading)
        verify(observer).onChanged(State.Error)

    }

    @Test
    fun `should fail to fetch chart data and try again`() {

        val exception = IOException()
        given { repository.getChartData() }
            .willReturn(Observable.error(exception))

        val vm = createViewModel()

        vm.state.observeForever(observer)
        vm.onAction(Action.Load)

        verify(observer).onChanged(State.Loading)
        verify(observer).onChanged(State.Error)

        given { repository.getChartData() }
            .willReturn(Observable.just(chartData))

        vm.onAction(Action.TryAgain)

        verify(observer, times(2)).onChanged(State.Loading)
        verify(observer).onChanged(State.Success(chartUiModel))
    }

    @Test
    fun `should clear disposable on onClear`() {
        val exception = IOException()
        given { repository.getChartData() }
            .willReturn(Observable.error(exception))
        val vm = createViewModel()

        vm.onAction(Action.Load)

        vm.callOnCleared()

        verify(disposable).clear()
    }

    @Test
    fun `given Exit action state should be End`() {
        val vm = createViewModel()
        vm.state.observeForever(observer)

        vm.onAction(Action.Exit)

        verify(observer).onChanged(State.End)
    }


    private fun createViewModel(): ChartActivityViewModel {
        return ChartActivityViewModel(
            realState = MutableLiveData(),
            disposables = disposable,
            mainScheduler = mainScheduler,
            ioScheduler = ioScheduler,
            repository = repository
        )
    }


}