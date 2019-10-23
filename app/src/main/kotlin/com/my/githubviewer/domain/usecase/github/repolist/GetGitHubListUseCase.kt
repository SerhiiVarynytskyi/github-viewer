package com.my.githubviewer.domain.usecase.github.repolist

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.my.githubviewer.domain.common.*
import com.my.githubviewer.domain.entity.Entity
import com.my.githubviewer.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineScope

interface GetGitHubListUseCase : BaseUseCase {

    fun getRepos(): LiveData<PagedList<Entity.GitHubRepo>>

    fun getBoundaryState(): LiveData<BoundaryState<Long>>

    fun fetchMore(itemDate: Long, direction: Direction, scope: CoroutineScope) : LiveData<State>

    fun refresh()

}