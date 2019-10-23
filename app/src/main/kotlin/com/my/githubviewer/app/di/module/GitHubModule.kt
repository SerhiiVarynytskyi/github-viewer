package com.my.githubviewer.app.di.module

import android.content.Context
import com.my.githubviewer.data.api.GitRepoApi
import com.my.githubviewer.data.datasource.github.GitHubApiDataSource
import com.my.githubviewer.data.datasource.github.GitHubApiDataSourceImpl
import com.my.githubviewer.data.datasource.github.GitHubDBDataSource
import com.my.githubviewer.data.datasource.github.GitHubDBDataSourceImpl
import com.my.githubviewer.data.db.github.repo.RepoDao
import com.my.githubviewer.data.repository.github.GitHubRepositoryImpl
import com.my.githubviewer.domain.common.logger.Logger
import com.my.githubviewer.domain.repository.github.GitHubRepository
import com.my.githubviewer.domain.usecase.github.repodetail.GetGitHubDetailsUseCase
import com.my.githubviewer.domain.usecase.github.repodetail.GetGitHubDetailsUseCaseImpl
import com.my.githubviewer.domain.usecase.github.repolist.GetGitHubListUseCase
import com.my.githubviewer.domain.usecase.github.repolist.GetGitHubListUseCaseImpl
import com.my.githubviewer.presentation.common.platform.NetworkHandlerImpl
import dagger.Module
import dagger.Provides

@Module
class GitHubModule {

    @Provides
    fun provideDatabaseSource(repoDao: RepoDao, log: Logger): GitHubDBDataSource =
        GitHubDBDataSourceImpl(repoDao, log)

    @Provides
    fun provideApiSource(api: GitRepoApi, log: Logger): GitHubApiDataSource =
        GitHubApiDataSourceImpl(api, log)

    @Provides
    fun provideGitListRepository(
        context: Context,
        apiSource: GitHubApiDataSource,
        dbSource: GitHubDBDataSource,
        log: Logger
    ): GitHubRepository = GitHubRepositoryImpl(NetworkHandlerImpl(context), apiSource, dbSource, log)

    @Provides
    fun provideGetGitHubListUseCase(repository: GitHubRepository, log: Logger): GetGitHubListUseCase =
        GetGitHubListUseCaseImpl(repository, log)

    @Provides
    fun provideGetGitHubDetailsUseCase(repository: GitHubRepository): GetGitHubDetailsUseCase =
        GetGitHubDetailsUseCaseImpl(repository)
}