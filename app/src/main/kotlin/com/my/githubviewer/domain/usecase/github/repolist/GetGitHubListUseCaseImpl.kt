package com.my.githubviewer.domain.usecase.github.repolist

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.my.githubviewer.domain.common.BoundaryState
import com.my.githubviewer.domain.common.Direction
import com.my.githubviewer.domain.common.State
import com.my.githubviewer.domain.common.logger.Logger
import com.my.githubviewer.domain.entity.Entity
import com.my.githubviewer.domain.repository.github.GitHubRepository
import kotlinx.coroutines.CoroutineScope

class GetGitHubListUseCaseImpl(private val repository: GitHubRepository, private val log : Logger) : GetGitHubListUseCase {

    override fun getRepos(): LiveData<PagedList<Entity.GitHubRepo>> {
        return repository.getRepos()
    }

    override fun getBoundaryState(): LiveData<BoundaryState<Long>> {
        return repository.getBoundaryState()
    }

    override fun refresh() {
        repository.refresh()
    }

    override fun fetchMore(itemDate: Long, direction: Direction, scope: CoroutineScope): LiveData<State> {
        val fetchId = when (direction) {
            Direction.BOTTOM -> (itemDate + 1)
            Direction.TOP -> -1
            else -> if (itemDate == 0L) itemDate else -1
        }

        if (log.isDebug) {
            log.d("fetchMore itemDate= $itemDate, direction= $direction, fetchId= $fetchId")
        }

        return if (fetchId >= 0) {
            repository.fetchMore(fetchId, scope)
        } else {
            repository.returnLoadingOrSuccess()
        }
    }
}