package com.my.githubviewer.data.datasource.github

import com.my.githubviewer.data.datasource.BaseDataSource
import com.my.githubviewer.domain.common.Result
import com.my.githubviewer.domain.common.State
import com.my.githubviewer.domain.entity.Entity

interface GitHubApiDataSource : BaseDataSource {
    suspend fun getRepositories(fetchId: Long): Result<List<Entity.GitHubRepo>, State.Failure>
}