package com.my.githubviewer.data.repository.github

import androidx.lifecycle.MutableLiveData
import com.my.githubviewer.data.DEFAULT_LIST_SIZE
import com.my.githubviewer.data.datasource.github.GitHubApiDataSource
import com.my.githubviewer.data.datasource.github.GitHubDBDataSourceImpl
import com.my.githubviewer.data.datasource.MockDataSourceFactory
import com.my.githubviewer.data.db.github.repo.RepoDao
import com.my.githubviewer.data.generateListOfMockRepos
import com.my.githubviewer.data.generateMockEntityRepo
import com.my.githubviewer.data.transformer.map
import com.my.githubviewer.domain.common.Result
import com.my.githubviewer.domain.common.State
import com.my.githubviewer.domain.common.logger.Logger
import com.my.githubviewer.domain.common.platform.INetworkHandler
import com.my.githubviewer.domain.entity.Entity
import com.my.githubviewer.test.extensions.InstantExecutorExtension
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class GitHubRepositoryTest {

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

    @BeforeEach
    fun setUpMock() {
        clearAllMocks()
    }

    @Test
    @ExtendWith(InstantExecutorExtension::class)
    fun `verify repo returned from database`() {
        // Arrange
        val repoId = 10L
        every {
            mockRepoDao.getRepo(repoId)
        } returns MutableLiveData<Entity.GitHubRepo>(generateMockEntityRepo(repoId))
        val expectedRepo = generateMockEntityRepo(repoId)

        // Exercise
        val result = mockRepository.getRepo(repoId)

        // Assert
        result.observeForever { repo ->
            assertEquals(expectedRepo, repo)
        }
    }

    @Test
    @ExtendWith(InstantExecutorExtension::class)
    fun `verify repositories returned from database`() {
        // Arrange
        every {
            mockRepoDao.getDataSourceFactory()
        } returns MockDataSourceFactory()

        // Exercise
        mockRepository.getRepos().observeForever { result ->
            assertEquals(result.size, DEFAULT_LIST_SIZE)
        }
    }

    //Verify fetchMore works as expected
    @Nested
    @ExtendWith(InstantExecutorExtension::class)
    inner class GetGitHubRepositories {

        @Test
        fun `verify good network connection returns result`() = runBlocking {
            // Arrange
            val expectedResult = listOf(State.Loading, State.HasData)
            every { mockNetworkHandler.isConnectedToInternet() } returns true
            coEvery {
                mockApiSource.getRepositories(any())
            } returns Result.Data(generateListOfMockRepos(100).map { it.map() })

            // Exercise
            val result = mockRepository.fetchMore(0, this)

            // Assert
            result.observeForever { resultState ->
                assertThat(expectedResult, hasItem(resultState))
            }
        }

        @Test
        fun `verify failed internet connection`() = runBlocking {
            // Arrange
            every { mockNetworkHandler.isConnectedToInternet() } returns false

            // Exercise
            val result = mockRepository.fetchMore(0, this)

            // Assert
            result.observeForever { r ->
                assertEquals(r, State.Failure.NotInternetConnected)
            }
        }
    }




}