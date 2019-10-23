package com.my.githubviewer.presentation.ui.repositories

import android.content.Context
import android.content.Intent
import com.my.githubviewer.presentation.ui.base.BaseActivity

class RepositoriesActivity : BaseActivity() {

    companion object {
        fun intent(context: Context) = Intent(context, RepositoriesActivity::class.java)
    }

    override fun fragment() = RepositoriesFragment.newInstance()

}