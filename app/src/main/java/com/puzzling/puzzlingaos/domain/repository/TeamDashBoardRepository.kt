package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.model.response.ResponseTeamPuzzleBoardDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamReviewListDto
import com.puzzling.puzzlingaos.domain.entity.TeamPuzzleBoard
import com.puzzling.puzzlingaos.domain.entity.TeamRanking

interface TeamDashBoardRepository {

    suspend fun getTeamRetroList(
        projectId: Int,
        startDate: String,
        endDate: String,
    ): Result<ResponseTeamReviewListDto>

    suspend fun getTeamPuzzle(
        projectId: Int,
        today: String,
    ): Result<ResponseTeamPuzzleBoardDto>

    suspend fun getTeamPuzzleBoard(
        projectId: Int,
        today: String,
    ): Result<List<TeamPuzzleBoard>>

    suspend fun getTeamRanking(
        projectId: Int,
    ): Result<List<TeamRanking>>
}
