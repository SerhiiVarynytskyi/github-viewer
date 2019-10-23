package com.my.githubviewer.data

import com.my.githubviewer.data.api.GitRepoApi
import com.my.githubviewer.data.db.DBData
import com.my.githubviewer.domain.entity.Entity

const val DEFAULT_LIST_SIZE = 2

fun generateListOfMockRepos(size: Int = DEFAULT_LIST_SIZE): List<GitRepoApi.Dto.Repo> {
    return (1..size).map { generateMockRepo(it.toLong()) }
}

fun generateMockRepo(repoId: Long): GitRepoApi.Dto.Repo {
    return GitRepoApi.Dto.Repo(
        id = repoId,
        name = "name",
        fullName = "fullName",
        htmlUrl = "htmlUrl",
        description = "description",
        owner = generateMockOwner(repoId)
    )
}

fun generateMockOwner(ownerId: Long): GitRepoApi.Dto.Owner {
    return GitRepoApi.Dto.Owner(
        id = ownerId,
        login = "login",
        avatarUrl = "avatarUrl",
        gravatarId = "gravatarId",
        htmlUrl = "htmlUrl",
        type = "type"
    )
}


fun generateMockDBDataRepo(repoId: Long) = DBData.Repo(
    id = repoId,
    name = "name",
    fullName = "fullName",
    htmlUrl = "htmlUrl",
    description = "description",
    ownerId = repoId,
    ownerLogin = "login",
    ownerAvatarUrl = "avatarUrl",
    ownerGravatarId = "gravatarId",
    ownerHtmlUrl = "htmlUrl",
    ownerType = "type"
)


fun generateMockEntityRepo(repoId: Long) = Entity.GitHubRepo(
    id = repoId,
    name = "name",
    fullName = "fullName",
    htmlUrl = "htmlUrl",
    description = "description",
    ownerId = repoId,
    ownerLogin = "login",
    ownerAvatarUrl = "avatarUrl",
    ownerGravatarId = "gravatarId",
    ownerHtmlUrl = "htmlUrl",
    ownerType = "type"
)