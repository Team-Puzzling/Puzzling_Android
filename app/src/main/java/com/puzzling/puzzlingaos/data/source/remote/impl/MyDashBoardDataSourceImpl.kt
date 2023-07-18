package com.puzzling.puzzlingaos.data.source.remote.impl

import com.puzzling.puzzlingaos.data.model.response.BaseResponse
import com.puzzling.puzzlingaos.data.model.response.ResponseActionPlanDto
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPuzzleBoardDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProceedingProjectDto
import com.puzzling.puzzlingaos.data.service.PersonalReviewService
import com.puzzling.puzzlingaos.data.source.remote.MyDashBoardDataSource
import javax.inject.Inject

class MyDashBoardDataSourceImpl @Inject constructor(
    private val apiService: PersonalReviewService,
) : MyDashBoardDataSource {
    override suspend fun getMyPuzzleBoard(
        memberId: Int,
        projectId: Int,
        today: String,
    ): BaseResponse<ResponseMyPuzzleBoardDto> =
        apiService.getMyPuzzleBoard(memberId, projectId, today)

    override suspend fun getActionPlan(
        memberId: Int,
        projectId: Int,
    ): BaseResponse<ResponseActionPlanDto> = apiService.getMyActionPlan(memberId, projectId)

    override suspend fun getProceedingProject(memberId: Int): BaseResponse<ResponseProceedingProjectDto> =
        apiService.getProceedingProjects(memberId)
}
