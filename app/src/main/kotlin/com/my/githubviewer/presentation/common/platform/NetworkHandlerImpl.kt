package com.my.githubviewer.presentation.common.platform


import android.content.Context
import com.my.githubviewer.domain.common.platform.INetworkHandler
import com.my.githubviewer.presentation.common.extension.networkInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandlerImpl@Inject constructor(private val context: Context) : INetworkHandler {
    override fun isConnectedToInternet(): Boolean {
        return context.networkInfo?.isConnectedOrConnecting ?: false
    }
}