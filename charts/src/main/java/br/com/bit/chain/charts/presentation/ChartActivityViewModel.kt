package br.com.bit.chain.charts.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.bit.chain.charts.domain.ChartRepository
import br.com.bit.chain.components.chart.ChartUiModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Named

internal sealed class State {
    object Loading : State()
    data class Success(val uiModel: ChartUiModel) : State()
    object Error : State()
    object End : State()
}

internal sealed class Action {
    object Load : Action()
    object TryAgain : Action()
    object Exit : Action()
}

internal class ChartActivityViewModel @Inject constructor(
    private val realState: MutableLiveData<State>,
    private val disposables: CompositeDisposable,
    @Named("MainScheduler")
    private val mainScheduler: Scheduler,
    @Named("IOScheduler")
    private val ioScheduler: Scheduler,
    private val repository: ChartRepository
) : ViewModel() {


    // NOTE: The public state is read-only.
    val state: LiveData<State> = realState

    fun onAction(action: Action) {
        when (action) {
            Action.Load -> loadChart()
            Action.TryAgain -> loadChart()
            Action.Exit -> exit()
        }
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    private fun loadChart() {
        realState.value = State.Loading
        disposables.add(repository.getChartData()
            .map {
                it.toChartUiModel()
                }
            .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe({ uiModel ->
                    realState.value = State.Success(uiModel)
                }, {
                    realState.value = State.Error
                })
        )


    }

    private fun exit() {
        realState.value = State.End
    }

}