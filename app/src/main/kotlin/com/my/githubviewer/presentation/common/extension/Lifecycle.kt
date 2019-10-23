package com.my.githubviewer.presentation.common.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.my.githubviewer.domain.common.State


fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<State>> LifecycleOwner.observeState(liveData: L, body: (State?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <L : LiveData<State.Failure>> LifecycleOwner.failure(liveData: L, body: (State.Failure?) -> Unit) =
    liveData.observe(this, Observer(body))
