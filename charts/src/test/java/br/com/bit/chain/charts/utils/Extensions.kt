package br.com.bit.chain.charts.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

// https://stackoverflow.com/a/57052682/2742962
fun ViewModel.callOnCleared() {
    val viewModelStore = ViewModelStore()
    val viewModelProvider = ViewModelProvider(viewModelStore, object : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = this@callOnCleared as T
    })
    viewModelProvider.get(this@callOnCleared::class.java)
    viewModelStore.clear()
}
