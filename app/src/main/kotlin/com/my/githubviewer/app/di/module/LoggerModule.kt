package com.my.githubviewer.app.di.module

import com.my.githubviewer.app.logger.LoggerImpl
import com.my.githubviewer.domain.common.logger.Logger
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoggerModule {

    @Provides
    @Singleton
    fun provideLogger(): Logger = LoggerImpl()

}