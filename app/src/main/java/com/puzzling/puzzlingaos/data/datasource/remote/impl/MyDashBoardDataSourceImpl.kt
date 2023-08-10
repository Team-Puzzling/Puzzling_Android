package com.puzzling.puzzlingaos.data.datasource.remote.impl

import com.puzzling.puzzlingaos.data.datasource.remote.MyDashBoardDataSource
import com.puzzling.puzzlingaos.data.model.response.ResponseActionPlanDto
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPuzzleBoardDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProceedingProjectDto
import com.puzzling.puzzlingaos.data.service.PersonalReviewService
import javax.inject.Inject

class MyDashBoardDataSourceImpl @Inject constructor(
    private val apiService: PersonalReviewService,
) : MyDashBoardDataSource {
    override suspend fun getUserPuzzle(
        memberId: Int,
        projectId: Int,
        today: String,
    ): ResponseMyPuzzleBoardDto =
        apiService.getMyPuzzleBoard(memberId, projectId, today)

    override suspend fun getActionPlan(
        memberId: Int,
        projectId: Int,
    ): ResponseActionPlanDto = apiService.getMyActionPlan(memberId, projectId)

    override suspend fun getProceedingProject(memberId: Int): ResponseProceedingProjectDto =
        apiService.getProceedingProjects(memberId)
}
