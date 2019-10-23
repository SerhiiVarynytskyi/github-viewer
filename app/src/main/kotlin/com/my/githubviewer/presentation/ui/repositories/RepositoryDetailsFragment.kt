package com.my.githubviewer.presentation.ui.repositories

import android.os.Bundle
import com.my.githubviewer.R
import com.my.githubviewer.databinding.FragmentRepositorieDetailsBinding
import com.my.githubviewer.presentation.common.extension.viewModel
import com.my.githubviewer.presentation.ui.base.BaseFragment


class RepositoryDetailsFragment : BaseFragment<FragmentRepositorieDetailsBinding, RepositoryDetailsViewModel>() {

    companion object {
        private const val PARAM_REPO_ID = "param_repo_id"

        fun newInstance(repoId: Long) : RepositoryDetailsFragment {
            val arguments = Bundle()
            val fragment = RepositoryDetailsFragment()
            arguments.putLong(PARAM_REPO_ID, repoId)
            fragment.arguments = arguments
            return fragment
        }
    }


    override fun layoutId() = R.layout.fragment_repositorie_details

    override fun buildViewModel(): RepositoryDetailsViewModel {
        if (log.isDebug) {
            log.d("buildViewModel")
        }
        return viewModel(viewModelFactory) {}
    }

    override fun initializeView() {
        super.initializeView()
        viewModel.setRepoId(getRepoId())
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    private fun getRepoId(): Long {
        return arguments?.getLong(PARAM_REPO_ID) ?: throw RuntimeException("Need set repo id")
    }

}