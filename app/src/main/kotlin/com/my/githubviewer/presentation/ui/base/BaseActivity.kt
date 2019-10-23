package com.my.githubviewer.presentation.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.my.githubviewer.R
import com.my.githubviewer.presentation.common.extension.inTransaction
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_base_layout.*

abstract class BaseActivity : DaggerAppCompatActivity() {

    abstract fun fragment(): Fragment?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_layout)
        setSupportActionBar(toolbar)
        setFragment(savedInstanceState)
    }

    private fun setFragment(savedInstanceState: Bundle?) {
        val fragment = fragment()
        if (fragment != null && savedInstanceState == null) {
            supportFragmentManager.inTransaction {
                replace(R.id.fragmentContainer, fragment)
            }
        }

    }

}