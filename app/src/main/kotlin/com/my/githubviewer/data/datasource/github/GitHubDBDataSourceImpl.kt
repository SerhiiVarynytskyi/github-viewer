package com.my.githubviewer.data.datasource.github

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.my.githubviewer.data.db.github.repo.RepoDao
import com.my.githubviewer.data.transformer.map
import com.my.githubviewer.domain.common.BoundaryState
import com.my.githubviewer.domain.common.logger.Logger
import com.my.githubviewer.domain.entity.Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubDBDataSourceImpl(
    private val repoDao: RepoDao,
    log: Logger
) : GitHubDBDataSource {

    private val boundaryCallback = BoundaryCallback(log)

    override suspend fun insertRepos(reposList: List<Entity.GitHubRepo>) =
        withContext(Dispatchers.IO) {
            repoDao.insertRepos(reposList.map { item -> item.map() })
        }

    override fun deleteRepos() {
        repoDao.deleteRepos()
    }

    override fun getRepos(): LiveData<PagedList<Entity.GitHubRepo>> {
        val dataSourceFactory = repoDao.getDataSourceFactory()
        return LivePagedListBuilder(dataSourceFactory, BoundaryCallback.DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }

    override fun refresh() {
        boundaryCallback.refresh()
    }

    override fun getBoundaryState(): LiveData<BoundaryState<Long>> {
        return boundaryCallback.boundaryState
    }

    override fun getRepo(id: Long): LiveData<Entity.GitHubRepo> {
        return repoDao.getRepo(id)
    }
}