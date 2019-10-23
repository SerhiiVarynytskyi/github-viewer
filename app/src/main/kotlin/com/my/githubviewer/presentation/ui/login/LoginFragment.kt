package com.my.githubviewer.presentation.ui.login

import android.os.Bundle
import com.my.githubviewer.R
import com.my.githubviewer.databinding.FragmentLoginBinding
import com.my.githubviewer.domain.common.State
import com.my.githubviewer.presentation.common.extension.observeState
import com.my.githubviewer.presentation.common.extension.viewModel
import com.my.githubviewer.presentation.ui.base.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun layoutId() = R.layout.fragment_login

    override fun buildViewModel(): LoginViewModel {
        return viewModel(viewModelFactory) {
            observeState(state) { handleState(it) }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }


    override fun handleFailure(failure: State.Failure?): Boolean {
        if (!super.handleFailure(failure)) {
            when (failure) {
                is LoginFailure.EmailOrPasswordIsEmpty -> renderFailure(R.string.email_or_password_is_empty)
                else -> {
                    return false
                }
            }
        }
        return true
    }
}