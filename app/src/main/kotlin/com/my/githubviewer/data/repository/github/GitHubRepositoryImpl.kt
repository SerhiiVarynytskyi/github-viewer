package com.my.githubviewer.data.repository.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.my.githubviewer.data.datasource.github.GitHubApiDataSource
import com.my.githubviewer.data.datasource.github.GitHubDBDataSource
import com.my.githubviewer.data.repository.BaseRepositoryImpl
import com.my.githubviewer.domain.common.*
import com.my.githubviewer.domain.common.logger.Logger
import com.my.githubviewer.domain.common.platform.INetworkHandler
import com.my.githubviewer.domain.entity.Entity
import com.my.githubviewer.domain.repository.github.GitHubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GitHubRepositoryImpl(

    private val networkHandler: INetworkHandler,
    private val apiSource: GitHubApiDataSource,
    private val localData: GitHubDBDataSource,
    private val log: Logger

) : BaseRepositoryImpl<Entity.GitHubRepo>(), GitHubRepository {

    private var lastFetchId = -1L
    private val status = MutableLiveData<State>()

    override fun getRepo(id: Long): LiveData<Entity.GitHubRepo> {
        return localData.getRepo(id)
    }

    override fun getRepos(): LiveData<PagedList<Entity.GitHubRepo>> {
        return localData.getRepos()
    }

    override fun getBoundaryState(): LiveData<BoundaryState<Long>> {
        return localData.getBoundaryState()
    }

    override fun returnLoadingOrSuccess(): LiveData<State> {
        if (status.value == null || status.value != State.Loading) {
            status.value = State.HasData
        }
        return status
    }

    override fun refresh() {
        lastFetchId = -1
        localData.refresh()
    }

    override fun fetchMore(fetchId: Long, scope: CoroutineScope): LiveData<State> {
        if (status.value == null || status.value != State.Loading) {

            if (lastFetchId >= fetchId) {
                return returnLoadingOrSuccess()
            }

            status.value = State.Loading
            if (log.isDebug) {
                log.d("fetchMore starting: $fetchId")
            }

            if (networkHandler.isConnectedToInternet()) {
                lastFetchId = fetchId
                scope.launch {
                    apiSource.getRepositories(fetchId).either(
                        onError = { status.value = it },
                        onSuccess = {
                            localData.insertRepos(it)
                            status.value = State.HasData
                            if (log.isDebug) {
                                log.d("fetchMore saved: $fetchId")
                            }
                        }
                    )
                }
            } else {
                status.value = State.Failure.NotInternetConnected
            }

        } else {
            if (log.isDebug) {
                log.d("fetchMore already loading id= $fetchId")
            }
        }
        return status
    }

}