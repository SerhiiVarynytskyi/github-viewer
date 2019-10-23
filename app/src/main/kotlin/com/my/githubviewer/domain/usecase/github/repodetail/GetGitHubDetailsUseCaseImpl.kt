package com.my.githubviewer.domain.usecase.github.repodetail

import androidx.lifecycle.LiveData
import com.my.githubviewer.domain.entity.Entity
import com.my.githubviewer.domain.repository.github.GitHubRepository

class GetGitHubDetailsUseCaseImpl(private val repository: GitHubRepository) :
    GetGitHubDetailsUseCase {

    override fun getRepo(id: Long): LiveData<Entity.GitHubRepo> {
        return repository.getRepo(id)
    }
}