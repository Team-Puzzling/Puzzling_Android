package com.puzzling.puzzlingaos.data.repository

import com.puzzling.puzzlingaos.data.model.response.ResponseActionPlanDto
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPuzzleBoardDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProceedingProjectDto
import com.puzzling.puzzlingaos.data.source.remote.MyDashBoardDataSource
import com.puzzling.puzzlingaos.domain.repository.MyBoardRepository
import javax.inject.Inject

class MyBoardRepositoryImpl @Inject constructor(
    private val myDashBoardDataSource: MyDashBoardDataSource,
) : MyBoardRepository {
    override suspend fun getMyPuzzleBoard(
        memberId: Int,
        projectId: Int,
        today: String,
    ): Result<ResponseMyPuzzleBoardDto> = runCatching {
        myDashBoardDataSource.getMyPuzzleBoard(memberId, projectId, today).data!!
    }

    override suspend fun getActionPlan(
        memberId: Int,
        projectId: Int,
    ): Result<ResponseActionPlanDto> = runCatching {
        myDashBoardDataSource.getActionPlan(memberId, projectId).data!!
    }

    override suspend fun getProceedingProject(memberId: Int): Result<ResponseProceedingProjectDto> =
        runCatching {
            myDashBoardDataSource.getProceedingProject(memberId).data!!
        }
}
