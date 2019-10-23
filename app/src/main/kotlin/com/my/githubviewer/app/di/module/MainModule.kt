package com.my.githubviewer.app.di.module

import com.my.githubviewer.presentation.ui.login.LoginActivity
import com.my.githubviewer.presentation.ui.login.LoginFragment
import com.my.githubviewer.presentation.ui.repositories.RepositoriesActivity
import com.my.githubviewer.presentation.ui.repositories.RepositoriesFragment
import com.my.githubviewer.presentation.ui.repositories.RepositoryDetailsActivity
import com.my.githubviewer.presentation.ui.repositories.RepositoryDetailsFragment
import com.my.githubviewer.presentation.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [])
internal abstract class MainModule {

    @ContributesAndroidInjector
    internal abstract fun splashActivity(): SplashActivity


    @ContributesAndroidInjector
    internal abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun loginFragment(): LoginFragment


    @ContributesAndroidInjector
    internal abstract fun repositoriesActivity(): RepositoriesActivity

    @ContributesAndroidInjector
    internal abstract fun repositoriesFragment(): RepositoriesFragment


    @ContributesAndroidInjector
    internal abstract fun repositoryDetailsActivity(): RepositoryDetailsActivity

    @ContributesAndroidInjector
    internal abstract fun repositoryDetailsFragment(): RepositoryDetailsFragment

}