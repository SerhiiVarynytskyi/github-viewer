package com.my.githubviewer.data.transformer


import com.my.githubviewer.data.generateMockEntityRepo
import com.my.githubviewer.data.generateMockRepo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DtoToEntityTest {

    @Test
    fun `test mapper Dto_Repo to Entity_GitHubRepo`() {
        val repoId = 10L
        val dtoRepo = generateMockRepo(repoId)
        val expectedRepo = generateMockEntityRepo(repoId)
        val actualRepo = dtoRepo.map()
        assertEquals(expectedRepo, actualRepo)
    }

}