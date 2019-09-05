package br.com.bit.chain.charts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.bit.chain.R
import br.com.bit.chain.app.BitApplication
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var app: BitApplication

    @field:[Inject Named("HELLO_WORLD")]
    lateinit var hello: String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        world.text = hello
    }
}
