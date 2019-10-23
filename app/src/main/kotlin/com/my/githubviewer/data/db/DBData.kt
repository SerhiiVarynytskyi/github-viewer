package com.my.githubviewer.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

sealed class DBData {

    @Entity(tableName = "github_repo_table")
    data class Repo(
        @PrimaryKey(autoGenerate = false)  @ColumnInfo(name = "id") val id: Long,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "fullName") val fullName: String,
        @ColumnInfo(name = "htmlUrl") val htmlUrl: String,
        @ColumnInfo(name = "description") val description: String,
        @ColumnInfo(name = "ownerId") val ownerId: Long,
        @ColumnInfo(name = "ownerLogin") val ownerLogin: String,
        @ColumnInfo(name = "ownerAvatarUrl") val ownerAvatarUrl: String,
        @ColumnInfo(name = "ownerGravatarId") val ownerGravatarId: String,
        @ColumnInfo(name = "ownerHtmlUrl") val ownerHtmlUrl: String,
        @ColumnInfo(name = "ownerType") val ownerType: String
    ) : DBData()

}