package com.my.githubviewer.presentation.ui.repositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.my.githubviewer.databinding.RowRepositorieBinding
import com.my.githubviewer.domain.entity.Entity

class RepositoriesListAdapter(private val listener: (Long) -> Unit)
    : PagedListAdapter<Entity.GitHubRepo, RepositoriesListAdapter.ViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowRepositorieBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = getItem(position)
        if (repo != null) {
            holder.bind(repo, listener)
        }
    }

    class ViewHolder(private var binding: RowRepositorieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Entity.GitHubRepo, listener: (Long) -> Unit) {
            binding.repository = repo
            binding.root.setOnClickListener { listener.invoke(repo.id) }
            binding.executePendingBindings()
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Entity.GitHubRepo>() {
            override fun areItemsTheSame(oldItem: Entity.GitHubRepo, newItem: Entity.GitHubRepo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Entity.GitHubRepo, newItem: Entity.GitHubRepo): Boolean =
                oldItem == newItem
        }
    }

}