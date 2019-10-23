package com.my.githubviewer.data.db.github.repo

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.my.githubviewer.data.db.DBData
import com.my.githubviewer.domain.entity.Entity

@Dao
abstract class RepoDao {

    @Query("SELECT id, name, fullName, htmlUrl, description, ownerId, ownerLogin, ownerAvatarUrl, ownerGravatarId, ownerHtmlUrl, ownerType  FROM github_repo_table ORDER BY id ASC")
    abstract fun getDataSourceFactory(): DataSource.Factory<Int, Entity.GitHubRepo>

    @Query("SELECT * FROM github_repo_table WHERE id= :id")
    abstract fun getRepo(id: Long) : LiveData<Entity.GitHubRepo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertRepos(repoList: List<DBData.Repo>)

    @Query("DELETE FROM github_repo_table")
    abstract fun deleteRepos()

}