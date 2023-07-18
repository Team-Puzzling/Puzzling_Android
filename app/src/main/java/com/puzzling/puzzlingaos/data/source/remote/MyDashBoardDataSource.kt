package com.puzzling.puzzlingaos.data.source.remote

import com.puzzling.puzzlingaos.data.model.response.BaseResponse
import com.puzzling.puzzlingaos.data.model.response.ResponseActionPlanDto
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPuzzleBoardDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProceedingProjectDto

interface MyDashBoardDataSource {
    suspend fun getMyPuzzleBoard(
        memberId: Int,
        projectId: Int,
        today: String,
    ): BaseResponse<ResponseMyPuzzleBoardDto>

    suspend fun getActionPlan(
        memberId: Int,
        projectId: Int,
    ): BaseResponse<ResponseActionPlanDto>

    suspend fun getProceedingProject(
        memberId: Int,
    ): BaseResponse<ResponseProceedingProjectDto>
}
