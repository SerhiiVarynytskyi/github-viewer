package com.my.githubviewer.app.di.component

import com.my.githubviewer.app.App
import com.my.githubviewer.app.di.module.AppModule
import com.my.githubviewer.app.di.module.MainModule
import com.my.githubviewer.app.di.module.LoginModule
import com.my.githubviewer.app.di.module.GitHubModule
import com.my.githubviewer.app.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        MainModule::class,
        ViewModelModule::class,
        LoginModule::class,
        GitHubModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}