package com.my.githubviewer.data.datasource.github

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.my.githubviewer.domain.common.BoundaryState
import com.my.githubviewer.domain.common.logger.Logger
import com.my.githubviewer.domain.entity.Entity

class BoundaryCallback(private val log: Logger) :
    PagedList.BoundaryCallback<Entity.GitHubRepo>() {

    private val _boundaryState = MutableLiveData<BoundaryState<Long>>()
    val boundaryState: LiveData<BoundaryState<Long>>
        get() = _boundaryState

    companion object {
        const val DATABASE_PAGE_SIZE = 15
    }

    override fun onItemAtFrontLoaded(itemAtFront: Entity.GitHubRepo) {
        if (log.isDebug) {
            log.d("onItemAtFrontLoaded ${itemAtFront.id} ${itemAtFront.fullName} ${itemAtFront.hashCode()}")
        }
        _boundaryState.value = BoundaryState.itemLoadedAtTop(itemAtFront.id)
    }

    override fun onZeroItemsLoaded() {
        if (log.isDebug) {
            log.d("onZeroItemsLoaded")
        }
        _boundaryState.value = BoundaryState.zeroItemsLoaded(0)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Entity.GitHubRepo) {
        if (log.isDebug) {
            log.d("onItemAtFrontLoaded ${itemAtEnd.id} ${itemAtEnd.fullName} ${itemAtEnd.hashCode()}")
        }
        _boundaryState.value = BoundaryState.itemLoadedAtBottom(itemAtEnd.id)
    }

    fun refresh() {
        if (log.isDebug) {
            log.d("refresh")
        }
        _boundaryState.value = BoundaryState.zeroItemsLoaded(0)
    }

}