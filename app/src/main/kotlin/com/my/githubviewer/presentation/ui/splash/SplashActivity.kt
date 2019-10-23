package com.my.githubviewer.presentation.ui.splash

import android.os.Bundle
import com.my.githubviewer.presentation.common.navigation.Navigator
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    internal lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.showMain(this)
        finish()
    }

}