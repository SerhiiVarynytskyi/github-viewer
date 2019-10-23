package com.my.githubviewer.data.transformer

import com.my.githubviewer.data.generateMockDBDataRepo
import com.my.githubviewer.data.generateMockEntityRepo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DataToEntityTest {

    @Test
    fun `test mapper DBData_Repo to Entity_GitHubRepo`() {
        val repoId = 10L
        val dbDataRepo = generateMockDBDataRepo(repoId)
        val expectedRepo = generateMockEntityRepo(repoId)
        val actualRepo = dbDataRepo.map()
        assertEquals(expectedRepo, actualRepo)
    }

}