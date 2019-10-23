package com.my.githubviewer.presentation.ui.repositories

import androidx.lifecycle.MutableLiveData
import com.my.githubviewer.data.generateMockEntityRepo
import com.my.githubviewer.domain.entity.Entity
import com.my.githubviewer.domain.usecase.github.repodetail.GetGitHubDetailsUseCase
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
class RepositoryDetailsViewModelTest {

    private lateinit var viewModel: RepositoryDetailsViewModel
    @MockK
    private lateinit var useCase: GetGitHubDetailsUseCase

    @BeforeEach
    fun setUpMock() {
        clearAllMocks()
        viewModel = RepositoryDetailsViewModel(useCase)
    }

    @Test
    @ExtendWith(InstantExecutorExtension::class)
    fun `loading repo details should update live data`() {
        // Arrange
        val repoId = 100L
        every {
            useCase.getRepo(repoId)
        } returns MutableLiveData<Entity.GitHubRepo>(generateMockEntityRepo(repoId))
        val expectedRepo = generateMockEntityRepo(repoId)

        // Assert
        viewModel.repo.observeForever { repo ->
            Assertions.assertEquals(expectedRepo, repo)
        }

        // Exercise
        viewModel.setRepoId(repoId)
    }

}