package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.model.response.ResponseMyPuzzleBoardDto
import com.puzzling.puzzlingaos.domain.entity.ActionPlan
import com.puzzling.puzzlingaos.domain.entity.Project

interface MyBoardRepository {
    suspend fun getMyPuzzleBoard(
        memberId: Int,
        projectId: Int,
        today: String,
    ): Result<ResponseMyPuzzleBoardDto>

    suspend fun getActionPlan(
        memberId: Int,
        projectId: Int,
    ): Result<List<ActionPlan>>

    suspend fun getProceedingProject(
        memberId: Int,
    ): Result<List<Project>>
}
