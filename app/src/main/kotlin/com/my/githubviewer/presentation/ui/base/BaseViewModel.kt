package com.my.githubviewer.presentation.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.my.githubviewer.domain.common.State
import com.my.githubviewer.domain.common.logger.Logger
import kotlinx.coroutines.*
import java.lang.RuntimeException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var log: Logger

    private var viewModelJob = Job()
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    open val state: LiveData<State> = MutableLiveData()

    protected fun handleState(s: State) {
        state.let {
            if (it is MutableLiveData<State>) {
                it.value = s
            } else {
                throw RuntimeException("state LiveData is not MutableLiveData")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun <P> executeInBG(block: suspend CoroutineScope.() -> P) {
        execute(block, viewModelScope, Dispatchers.IO)
    }

    fun <P> executeInMain(block: suspend CoroutineScope.() -> P) {
        execute(block, viewModelScope, Dispatchers.Main)
    }

    private inline fun <P> execute(
        crossinline block: suspend CoroutineScope.() -> P,
        scope: CoroutineScope,
        context: CoroutineContext
    ) {
        scope.launch {
            withContext(context) {
                block.invoke(this)
            }
        }
    }
}