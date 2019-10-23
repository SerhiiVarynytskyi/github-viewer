package com.my.githubviewer.app.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.my.githubviewer.presentation.ui.login.LoginViewModel
import com.my.githubviewer.presentation.ui.repositories.RepositoriesViewModel
import com.my.githubviewer.presentation.ui.repositories.RepositoryDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    abstract fun bindRepositoriesViewModel(viewModel: RepositoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryDetailsViewModel::class)
    abstract fun bindRepositoryDetailsViewModel(viewModel: RepositoryDetailsViewModel): ViewModel
}