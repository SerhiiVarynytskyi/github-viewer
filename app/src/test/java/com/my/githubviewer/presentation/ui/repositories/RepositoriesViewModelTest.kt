package com.my.githubviewer.presentation.ui.repositories

import com.my.githubviewer.data.datasource.asPagedList
import com.my.githubviewer.data.generateListOfMockRepos
import com.my.githubviewer.data.transformer.map
import com.my.githubviewer.domain.usecase.github.repolist.GetGitHubListUseCase
import com.my.githubviewer.test.extensions.InstantExecutorExtension
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class RepositoriesViewModelTest {

    companion object {
        const val LIST_SIZE = 2
    }

    private lateinit var viewModel: RepositoriesViewModel
    @MockK
    private lateinit var useCase: GetGitHubListUseCase

    @BeforeEach
    fun setUpMock() {
        clearAllMocks()
        // Arrange
        val repoList = generateListOfMockRepos(LIST_SIZE).map { it.map() }
        every { useCase.getRepos() } returns repoList.asPagedList()

        viewModel = RepositoriesViewModel(useCase)
    }

    @Test
    @ExtendWith(InstantExecutorExtension::class)
    fun `loading repos should update live data`() {
        // Arrange
        val expectedRepoList =  generateListOfMockRepos(LIST_SIZE).map { it.map() }

        // Assert
        viewModel.repos.observeForever { repo ->
            Assertions.assertEquals(repo.size, LIST_SIZE)
            Assertions.assertEquals(repo, expectedRepoList)
        }

    }
}