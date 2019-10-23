package com.my.githubviewer.app.di.module

import com.my.githubviewer.domain.usecase.user.UserUseCase
import com.my.githubviewer.domain.usecase.user.UserUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun provideUserUseCase(): UserUseCase = UserUseCaseImpl()

}