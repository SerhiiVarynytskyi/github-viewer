package com.my.githubviewer.presentation.common.adapters

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.my.githubviewer.domain.common.State

@BindingAdapter("uiState")
fun setUiStateForLoading(progressView: ProgressBar, state: State?) {
    progressView.visibility = when (state) {
        State.Loading -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("uiState")
fun setUiStateForSwipeRefreshLayout(swipeRefreshLayout: SwipeRefreshLayout, state: State?) {
    swipeRefreshLayout.isRefreshing = when (state) {
        State.Loading -> true
        else -> false
    }
}

@BindingAdapter("uiState")
fun setUiStateForLoadedContent(view: View, state: State) {
    view.visibility = when (state) {
        State.HasData -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("emptyState")
fun setUiStateForEmptyView(view: View, state: State) {
    view.visibility = when (state) {
        State.NoData -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter("errorState")
fun setUiStateForErrorView(view: View, state: State) {
    view.visibility = when (state) {
        is Error -> View.VISIBLE
        else -> View.GONE
    }
}

