package com.my.githubviewer.domain.entity

sealed class Entity {

    data class GitHubRepo(
        val id: Long,
        val name: String = "",
        val fullName: String = "",
        val htmlUrl: String = "",
        val description: String = "",

        val numberOfStars: Int? = 0,
        val hasIssues: Boolean? = false,

        val ownerId: Long = 0,
        val ownerLogin: String = "",
        val ownerAvatarUrl: String = "",
        val ownerGravatarId: String = "",
        val ownerHtmlUrl: String = "",
        val ownerType: String = ""

    ) : Entity()

}