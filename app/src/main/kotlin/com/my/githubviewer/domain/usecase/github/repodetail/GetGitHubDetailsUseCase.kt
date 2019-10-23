package com.my.githubviewer.domain.usecase.github.repodetail

import androidx.lifecycle.LiveData
import com.my.githubviewer.domain.entity.Entity
import com.my.githubviewer.domain.usecase.BaseUseCase

interface GetGitHubDetailsUseCase : BaseUseCase {

    fun getRepo(id: Long) : LiveData<Entity.GitHubRepo>

}