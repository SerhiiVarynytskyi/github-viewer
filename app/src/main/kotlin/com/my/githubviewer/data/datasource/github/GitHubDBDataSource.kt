package com.my.githubviewer.data.datasource.github

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.my.githubviewer.data.datasource.BaseDataSource
import com.my.githubviewer.domain.common.BoundaryState
import com.my.githubviewer.domain.entity.Entity

interface GitHubDBDataSource : BaseDataSource {

    suspend fun insertRepos(reposList: List<Entity.GitHubRepo>)
    fun deleteRepos()
    fun getRepos(): LiveData<PagedList<Entity.GitHubRepo>>
    fun refresh()
    fun getBoundaryState(): LiveData<BoundaryState<Long>>

    fun getRepo(id: Long) : LiveData<Entity.GitHubRepo>
}