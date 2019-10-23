package com.my.githubviewer.presentation.ui.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.my.githubviewer.domain.common.Direction
import com.my.githubviewer.domain.common.State
import com.my.githubviewer.domain.usecase.github.repolist.GetGitHubListUseCase
import com.my.githubviewer.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val getGitHubListUseCase: GetGitHubListUseCase
) : BaseViewModel() {

    val repos = getGitHubListUseCase.getRepos()

    override val state: LiveData<State>
        get() = Transformations.switchMap(getGitHubListUseCase.getBoundaryState()) {
            onBoundaryItemLoaded(it.itemData, it.direction)
        }

    private fun onBoundaryItemLoaded(itemDate: Long, direction: Direction) : LiveData<State> {
        if (log.isDebug) {
            log.d("onBoundaryItemLoaded $itemDate $direction")
        }
        return getGitHubListUseCase.fetchMore(itemDate, direction, viewModelScope)
    }

    fun refresh() {
        if (log.isDebug) {
            log.d("refreshing")
        }
        getGitHubListUseCase.refresh()
    }
}