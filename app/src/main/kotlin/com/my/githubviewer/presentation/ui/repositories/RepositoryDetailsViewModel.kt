package com.my.githubviewer.presentation.ui.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.my.githubviewer.domain.entity.Entity
import com.my.githubviewer.domain.usecase.github.repodetail.GetGitHubDetailsUseCase
import com.my.githubviewer.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class RepositoryDetailsViewModel @Inject constructor(
    private val getGitHubDetailsUseCase: GetGitHubDetailsUseCase
) : BaseViewModel() {

    private val _repoId = MutableLiveData<Long>()

    val repo : LiveData<Entity.GitHubRepo> = Transformations
        .switchMap(_repoId) { id ->
            getGitHubDetailsUseCase.getRepo(id)
        }

    fun setRepoId(id : Long){
        if(_repoId.value != id) {
            _repoId.value = id
        }
    }
}