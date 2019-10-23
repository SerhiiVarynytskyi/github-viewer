package com.my.githubviewer.presentation.ui.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.my.githubviewer.domain.usecase.user.UserUseCase
import com.my.githubviewer.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userUseCase: UserUseCase) : BaseViewModel() {

    val isLoading = ObservableField<Boolean>(false)
    val email = ObservableField<String>()
    val password = MutableLiveData<String>()

    fun login() {
        val emailString = email.get()
        val passwordString = password.value
        if (emailString.isNullOrEmpty() || passwordString.isNullOrEmpty()) {
            return
        }
        executeInBG {
            userUseCase.login(emailString, passwordString)
        }
    }

}