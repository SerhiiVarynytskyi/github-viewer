package com.my.githubviewer.presentation.ui.repositories

import android.content.Context
import android.content.Intent
import com.my.githubviewer.presentation.ui.base.BaseActivity

class RepositoryDetailsActivity : BaseActivity() {

    companion object {
        private const val EXTRA_PARAM_REPO_ID = "EXTRA_PARAM_REPO_ID"

        fun intent(context: Context, repoId: Long): Intent {
            val intent = Intent(context, RepositoryDetailsActivity::class.java)
            intent.putExtra(EXTRA_PARAM_REPO_ID, repoId)
            return intent
        }
    }

    override fun fragment() =
        RepositoryDetailsFragment.newInstance(intent.getLongExtra(EXTRA_PARAM_REPO_ID, 0))

}