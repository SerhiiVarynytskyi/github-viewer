package com.my.githubviewer.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitRepoApi {

    companion object {
        private const val PARAM_SINCE_ID = "since"
        private const val REPOSITORIES = "repositories"
    }


    @GET(REPOSITORIES)
    fun getGitHubRepo(@Query(PARAM_SINCE_ID) repoId: Long): Call<List<Dto.Repo>>


    sealed class Dto {
        companion object {
            private const val KEY_ID = "id"
            private const val KEY_NAME = "name"
            private const val KEY_FULL_NAME = "full_name"
            private const val KEY_HTML_URL = "html_url"
            private const val KEY_DESCRIPTION = "description"
            private const val KEY_OWNER = "owner"
            private const val KEY_LOGIN = "login"
            private const val KEY_AVATAR_URL = "avatar_url"
            private const val KEY_GRAVATAR_ID = "gravatar_id"
            private const val KEY_TYPE = "type"
        }

        data class Repo(
            @SerializedName(KEY_ID) val id: Long,
            @SerializedName(KEY_NAME) val name: String,
            @SerializedName(KEY_FULL_NAME) val fullName: String,
            @SerializedName(KEY_HTML_URL) val htmlUrl: String,
            @SerializedName(KEY_DESCRIPTION) val description: String?,
            @SerializedName(KEY_OWNER) val owner: Owner
        ) : Dto()

        data class Owner(
            @SerializedName(KEY_ID) val id: Long,
            @SerializedName(KEY_LOGIN) val login: String,
            @SerializedName(KEY_AVATAR_URL) val avatarUrl: String,
            @SerializedName(KEY_GRAVATAR_ID) val gravatarId: String,
            @SerializedName(KEY_HTML_URL) val htmlUrl: String,
            @SerializedName(KEY_TYPE) val type: String
        ) : Dto()

    }
}