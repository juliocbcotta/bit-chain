package br.com.bit.chain

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

import com.squareup.rx2.idler.Rx2Idler

import br.com.bit.chain.app.BitApplication
import io.reactivex.plugins.RxJavaPlugins

class BitTestRunner : AndroidJUnitRunner() {

    override fun onStart() {
        RxJavaPlugins.setInitComputationSchedulerHandler(
            Rx2Idler.create("RxJava 2.x Computation Scheduler")
        )
        RxJavaPlugins.setInitIoSchedulerHandler(
            Rx2Idler.create("RxJava 2.x IO Scheduler")
        )
        RxJavaPlugins.setInitNewThreadSchedulerHandler(
            Rx2Idler.create("RxJava 2.x New Thread Scheduler")
        )
        super.onStart()
    }

    @Throws(
        InstantiationException::class,
        IllegalAccessException::class,
        ClassNotFoundException::class
    )
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, BitApplication::class.java.name, context)
    }
}