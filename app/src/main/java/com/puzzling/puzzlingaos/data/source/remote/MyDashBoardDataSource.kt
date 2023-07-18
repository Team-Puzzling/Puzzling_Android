package com.puzzling.puzzlingaos.data.source.remote

import com.puzzling.puzzlingaos.data.model.response.ResponseActionPlanDto
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPuzzleBoardDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProceedingProjectDto

interface MyDashBoardDataSource {
    suspend fun getUserPuzzle(
        memberId: Int,
        projectId: Int,
        today: String,
    ): ResponseMyPuzzleBoardDto

    suspend fun getActionPlan(
        memberId: Int,
        projectId: Int,
    ): ResponseActionPlanDto

    suspend fun getProceedingProject(
        memberId: Int,
    ): ResponseProceedingProjectDto
}
