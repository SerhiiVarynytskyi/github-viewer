package com.my.githubviewer.presentation.common.navigation

import android.content.Context
import com.my.githubviewer.domain.usecase.user.UserUseCase
import com.my.githubviewer.presentation.ui.login.LoginActivity
import com.my.githubviewer.presentation.ui.repositories.RepositoriesActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor(private val userUseCase: UserUseCase) {

    private fun showLogin(context: Context) = context.startActivity(LoginActivity.intent(context))

    private fun showRepositories(context: Context) = context.startActivity(RepositoriesActivity.intent(context))

    fun showMain(context: Context) {
        when (userUseCase.userLoggedIn()) {
            true -> showRepositories(context)
            false -> showLogin(context)
        }
    }

}