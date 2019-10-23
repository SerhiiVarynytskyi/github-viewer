package com.my.githubviewer.presentation.ui.login

import android.content.Context
import android.content.Intent
import com.my.githubviewer.presentation.ui.base.BaseActivity

class LoginActivity : BaseActivity() {

    companion object {
        fun intent(context: Context) = Intent(context, LoginActivity::class.java)
    }

    override fun fragment() = LoginFragment.newInstance()

}