package com.my.githubviewer.domain.usecase.github.repolist

import com.my.githubviewer.data.datasource.github.GitHubApiDataSource
import com.my.githubviewer.data.datasource.github.GitHubDBDataSourceImpl
import com.my.githubviewer.data.db.github.repo.RepoDao
import com.my.githubviewer.data.generateListOfMockRepos
import com.my.githubviewer.data.repository.github.GitHubRepositoryImpl
import com.my.githubviewer.data.transformer.map
import com.my.githubviewer.domain.common.Direction
import com.my.githubviewer.domain.common.Result
import com.my.githubviewer.domain.common.State
import com.my.githubviewer.domain.common.logger.Logger
import com.my.githubviewer.domain.common.platform.INetworkHandler
import com.my.githubviewer.test.extensions.InstantExecutorExtension
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GetGitHubListUseCaseTest {

    @MockK(relaxed = true)
    private lateinit var mockLogger: Logger
    @MockK(relaxed = true)
    private lateinit var mockRepoDao: RepoDao
    @MockK
    private lateinit var mockApiSource: GitHubApiDataSource
    @MockK(relaxUnitFun = true)
    private lateinit var mockNetworkHandler: INetworkHandler

    private val mockLocalData: GitHubDBDataSourceImpl by lazy {
        GitHubDBDataSourceImpl(mockRepoDao, mockLogger)
    }

    private val mockRepository: GitHubRepositoryImpl by lazy {
        GitHubRepositoryImpl(
            networkHandler = mockNetworkHandler,
            apiSource = mockApiSource,
            localData = mockLocalData,
            log = mockLogger
        )
    }

    private val useCase : GetGitHubListUseCase by lazy {
        GetGitHubListUseCaseImpl(mockRepository, mockLogger)
    }


    @BeforeEach
    fun setUpMock() {
        clearAllMocks()
    }

    @Test
    @ExtendWith(InstantExecutorExtension::class)
    fun `should get data from GitHubRepository`() = runBlocking {
        // Arrange
        val expectedResult = listOf(State.Loading, State.HasData)
        every { mockNetworkHandler.isConnectedToInternet() } returns true
        coEvery {
            mockApiSource.getRepositories(any())
        } returns Result.Data(generateListOfMockRepos(100).map { it.map() })

        // Exercise
        val result = useCase.fetchMore(0, Direction.NONE, this)

        // Assert
        result.observeForever { resultState ->
            MatcherAssert.assertThat(expectedResult, Matchers.hasItem(resultState))
        }
    }

}