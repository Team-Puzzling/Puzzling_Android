package com.puzzling.puzzlingaos.data.repository

import com.puzzling.puzzlingaos.data.datasource.remote.MyDashBoardDataSource
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPuzzleBoardDto
import com.puzzling.puzzlingaos.domain.entity.ActionPlan
import com.puzzling.puzzlingaos.domain.entity.MyPuzzleBoard
import com.puzzling.puzzlingaos.domain.entity.Project
import com.puzzling.puzzlingaos.domain.repository.MyBoardRepository
import javax.inject.Inject

class MyBoardRepositoryImpl @Inject constructor(
    private val myDashBoardDataSource: MyDashBoardDataSource,
) : MyBoardRepository {
    override suspend fun getUserPuzzle(
        memberId: Int,
        projectId: Int,
        today: String,
    ): Result<ResponseMyPuzzleBoardDto> = runCatching {
        myDashBoardDataSource.getUserPuzzle(memberId, projectId, today)
    }

    override suspend fun getUserPuzzleBoard(
        memberId: Int,
        projectId: Int,
        today: String,
    ): Result<List<MyPuzzleBoard>> = runCatching {
        myDashBoardDataSource.getUserPuzzle(
            memberId,
            projectId,
            today,
        ).data.toPuzzleBoard()
    }

    override suspend fun getActionPlan(
        memberId: Int,
        projectId: Int,
    ): Result<List<ActionPlan>> = runCatching {
        myDashBoardDataSource.getActionPlan(memberId, projectId).toActionPlan()
    }

    override suspend fun getProceedingProject(memberId: Int): Result<List<Project>> =
        runCatching {
            myDashBoardDataSource.getProceedingProject(memberId).toProject()
        }
}
