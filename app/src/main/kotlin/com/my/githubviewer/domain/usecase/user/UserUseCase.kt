package com.my.githubviewer.domain.usecase.user

import com.my.githubviewer.domain.usecase.BaseUseCase

interface UserUseCase : BaseUseCase {
    fun userLoggedIn(): Boolean
    fun login(email: String, password: String)
}