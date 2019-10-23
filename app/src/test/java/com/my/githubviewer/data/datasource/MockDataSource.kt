package com.my.githubviewer.data.datasource

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.my.githubviewer.data.generateListOfMockRepos
import com.my.githubviewer.data.transformer.map
import com.my.githubviewer.domain.entity.Entity

class MockDataSourceFactory: DataSource.Factory<Int, Entity.GitHubRepo>() {
    override fun create(): DataSource<Int, Entity.GitHubRepo> {
        return MockLimitDataSource(
            generateListOfMockRepos().map { it.map() }
        )
    }
}

class MockLimitDataSource<T>(private val itemList: List<T>) : PageKeyedDataSource<Int, T>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, T>) {
        callback.onResult(itemList, null, null)
    }
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {}
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, T>) {}
}