package com.my.githubviewer.presentation.ui.login

import com.my.githubviewer.domain.common.State

class LoginFailure {
    class EmailOrPasswordIsEmpty: State.Failure.FeatureFailure()
}