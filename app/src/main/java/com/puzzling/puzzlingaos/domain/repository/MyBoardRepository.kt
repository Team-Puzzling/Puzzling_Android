package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.model.response.ResponseActionPlanDto
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPuzzleBoardDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProceedingProjectDto

interface MyBoardRepository {
    suspend fun getMyPuzzleBoard(
        memberId: Int,
        projectId: Int,
        today: String,
    ): Result<ResponseMyPuzzleBoardDto>

    suspend fun getActionPlan(
        memberId: Int,
        projectId: Int,
    ): Result<ResponseActionPlanDto>

    suspend fun getProceedingProject(
        memberId: Int,
    ): Result<ResponseProceedingProjectDto>
}
