package br.com.bit.chain.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.bit.chain.R
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.TimeUnit


class SplashActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        content.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

        disposable = Single.just(true)
            .delay(5, TimeUnit.SECONDS)
            .subscribe({
                val intent = Intent(Intent.ACTION_VIEW)
                    .setClassName(
                        packageName, "br.com.bit.chain.charts.presentation.ChartActivity"
                    )
                startActivity(intent)
                finish()
            }, {
                it.printStackTrace()
            })

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
