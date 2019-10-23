package com.my.githubviewer.presentation.ui.repositories

import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.my.githubviewer.R
import com.my.githubviewer.databinding.FragmentRepositoriesBinding
import com.my.githubviewer.domain.entity.Entity
import com.my.githubviewer.presentation.common.extension.observe
import com.my.githubviewer.presentation.common.extension.observeState
import com.my.githubviewer.presentation.common.extension.viewModel
import com.my.githubviewer.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_repositories.*


class RepositoriesFragment : BaseFragment<FragmentRepositoriesBinding, RepositoriesViewModel>() {

    companion object {
        fun newInstance() = RepositoriesFragment()
    }

    private val adapter: RepositoriesListAdapter by lazy {
        RepositoriesListAdapter { repoId ->
            context?.let {
                startActivity(RepositoryDetailsActivity.intent(it, repoId))
            }
        }
    }

    override fun layoutId() = R.layout.fragment_repositories

    override fun buildViewModel(): RepositoriesViewModel {
        if (log.isDebug) {
            log.d("buildViewModel")
        }

        return viewModel(viewModelFactory) {
            observe(repos, ::renderRepoList)
            observeState(state) { handleState(it) }
        }
    }

    override fun initializeView() {
        super.initializeView()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun renderRepoList(repoList: PagedList<Entity.GitHubRepo>?) {
        if (repoList != null) {
            adapter.submitList(repoList)
        }
    }

}