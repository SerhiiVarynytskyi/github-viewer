package com.my.githubviewer.data.datasource.github

import com.my.githubviewer.data.api.GitRepoApi
import com.my.githubviewer.data.transformer.map
import com.my.githubviewer.domain.common.Result
import com.my.githubviewer.domain.common.State
import com.my.githubviewer.domain.common.logger.Logger
import com.my.githubviewer.domain.entity.Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class GitHubApiDataSourceImpl(
    private val api: GitRepoApi,
    private val log: Logger
) : GitHubApiDataSource {

    override suspend fun getRepositories(fetchId: Long): Result<List<Entity.GitHubRepo>, State.Failure> =
        withContext(Dispatchers.IO) {
            requestExecute(
                api.getGitHubRepo(fetchId),
                { item -> item.map { it.map() } },
                emptyList()
            )
        }

    private fun <T, R> requestExecute(
        call: Call<T>,
        transform: (T) -> R,
        default: T
    ): Result<R, State.Failure> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Result.Data(transform((response.body() ?: default)))
                false -> Result.Error(State.Failure.ServerError)
            }
        } catch (exception: Throwable) {
            if (log.isDebug) {
                log.e(exception, "message= ${exception.message}")
            }
            Result.Error(State.Failure.CustomFailure(exception))
        }
    }
}