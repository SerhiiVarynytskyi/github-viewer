package com.my.githubviewer.domain.usecase.github.repodetail

import androidx.lifecycle.MutableLiveData
import com.my.githubviewer.data.generateMockEntityRepo
import com.my.githubviewer.domain.entity.Entity
import com.my.githubviewer.domain.repository.github.GitHubRepository
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
class GetGitHubDetailsUseCaseTest {

    @MockK
    private lateinit var mockRepository: GitHubRepository

    private val useCase : GetGitHubDetailsUseCaseImpl by lazy {
        GetGitHubDetailsUseCaseImpl(mockRepository)
    }

    @BeforeEach
    fun setUpMock() {
        clearAllMocks()
    }

    @Test
    @ExtendWith(InstantExecutorExtension::class)
    fun `should get data from GitHubRepository`() {
        // Arrange
        val repoId = 10L
        every {
            mockRepository.getRepo(repoId)
        } returns MutableLiveData<Entity.GitHubRepo>(generateMockEntityRepo(repoId))
        val expectedRepo = generateMockEntityRepo(repoId)

        // Exercise
        val result = useCase.getRepo(repoId)

        // Assert
        result.observeForever { repo ->
            Assertions.assertEquals(expectedRepo, repo)
        }
    }

}