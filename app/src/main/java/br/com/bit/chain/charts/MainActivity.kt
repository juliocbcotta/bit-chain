package br.com.bit.chain.charts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.bit.chain.R
import br.com.bit.chain.app.BitApplication
import br.com.bit.chain.charts.data.repository.ChartRepository
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: ChartRepository

    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        disposables.add(repository.getChart().subscribe({ chart ->
            println(chart)

        }, {
            it.printStackTrace()
        }))
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.clear()
    }
}
