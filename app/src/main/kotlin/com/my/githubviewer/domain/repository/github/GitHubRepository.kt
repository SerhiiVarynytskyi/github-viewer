package com.my.githubviewer.domain.repository.github

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.my.githubviewer.domain.common.*
import com.my.githubviewer.domain.entity.Entity
import com.my.githubviewer.domain.repository.BaseRepository
import kotlinx.coroutines.CoroutineScope

interface GitHubRepository : BaseRepository {

    fun getRepo(id: Long) : LiveData<Entity.GitHubRepo>

    fun getRepos(): LiveData<PagedList<Entity.GitHubRepo>>

    fun getBoundaryState(): LiveData<BoundaryState<Long>>

    fun returnLoadingOrSuccess() : LiveData<State>

    fun refresh()

    fun fetchMore(fetchId: Long, scope: CoroutineScope) : LiveData<State>

}