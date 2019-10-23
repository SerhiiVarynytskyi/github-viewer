package com.my.githubviewer.data.transformer

import com.my.githubviewer.data.db.DBData
import com.my.githubviewer.domain.entity.Entity

fun DBData.Repo.map() = Entity.GitHubRepo(
    id = this.id,
    name = this.name,
    fullName = this.fullName,
    htmlUrl = this.htmlUrl,
    description = this.description,
    ownerId = this.ownerId,
    ownerLogin = this.ownerLogin,
    ownerAvatarUrl = this.ownerAvatarUrl,
    ownerGravatarId = this.ownerGravatarId,
    ownerHtmlUrl = this.ownerHtmlUrl,
    ownerType = this.ownerType
)
