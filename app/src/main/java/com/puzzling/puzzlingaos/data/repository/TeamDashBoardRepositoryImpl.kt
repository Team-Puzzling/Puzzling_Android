package com.puzzling.puzzlingaos.data.repository

import android.util.Log
import com.puzzling.puzzlingaos.data.datasource.remote.TeamDashBoardDataSource
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamPuzzleBoardDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamReviewListDto
import com.puzzling.puzzlingaos.domain.entity.TeamPuzzleBoard
import com.puzzling.puzzlingaos.domain.entity.TeamRanking
import com.puzzling.puzzlingaos.domain.repository.TeamDashBoardRepository
import javax.inject.Inject

class TeamDashBoardRepositoryImpl @Inject constructor(
    private val teamDashBoardDataSource: TeamDashBoardDataSource,
) : TeamDashBoardRepository {
    override suspend fun getTeamRetroList(
        projectId: Int,
        startDate: String,
        endDate: String,
    ): Result<ResponseTeamReviewListDto> = runCatching {
        teamDashBoardDataSource.getTeamRetroList(projectId, startDate, endDate)
    }.onSuccess {
        Log.d("teamRetro: ", "팀원 회고 현황 조회 성공")
    }.onFailure {
        Log.d("teamRetro: ", "팀원 회고 현황 조회 실패")
    }

    override suspend fun getTeamPuzzle(
        projectId: Int,
        today: String,
    ): Result<ResponseTeamPuzzleBoardDto> = runCatching {
        teamDashBoardDataSource.getTeamPuzzle(projectId, today)
    }

    override suspend fun getTeamPuzzleBoard(
        projectId: Int,
        today: String,
    ): Result<List<TeamPuzzleBoard>> = runCatching {
        teamDashBoardDataSource.getTeamPuzzle(projectId, today).data.toPuzzleBoard()
    }

    override suspend fun getTeamRanking(projectId: Int): Result<List<TeamRanking>> =
        runCatching {
            teamDashBoardDataSource.getTeamRanking(projectId).toTeamRanking()
        }
}
