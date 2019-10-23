package com.my.githubviewer.data.transformer

import com.my.githubviewer.data.api.GitRepoApi
import com.my.githubviewer.domain.entity.Entity

fun GitRepoApi.Dto.Repo.map() = Entity.GitHubRepo(
    id = this.id,
    name = this.name,
    fullName = this.fullName,
    htmlUrl = this.htmlUrl,
    description = this.description ?: "",
    ownerId = this.owner.id,
    ownerLogin = this.owner.login,
    ownerAvatarUrl = this.owner.avatarUrl,
    ownerGravatarId = this.owner.gravatarId,
    ownerHtmlUrl = this.owner.htmlUrl,
    ownerType = this.owner.type
)
