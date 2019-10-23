package com.my.githubviewer.data.transformer

import com.my.githubviewer.data.generateMockDBDataRepo
import com.my.githubviewer.data.generateMockEntityRepo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EntityToDataTest {

    @Test
    fun `test mapper Entity_GitHubRepo to DBData_Repo`() {
        val repoId = 10L
        val entityRepo = generateMockEntityRepo(repoId)
        val expectedRepo = generateMockDBDataRepo(repoId)
        val actualRepo = entityRepo.map()
        assertEquals(expectedRepo, actualRepo)
    }

}