package com.puzzling.puzzlingaos.data.repository

import android.util.Log
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamPuzzleBoardDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamReviewListDto
import com.puzzling.puzzlingaos.data.source.remote.TeamReviewDataSource
import com.puzzling.puzzlingaos.domain.entity.TeamPuzzleBoard
import com.puzzling.puzzlingaos.domain.entity.TeamRanking
import com.puzzling.puzzlingaos.domain.repository.TeamReviewRepository
import javax.inject.Inject

class TeamReviewRepositoryImpl @Inject constructor(
    private val teamReviewDataSource: TeamReviewDataSource,
) : TeamReviewRepository {
    override suspend fun getTeamRetroList(
        projectId: Int,
        startDate: String,
        endDate: String,
    ): Result<ResponseTeamReviewListDto> = runCatching {
        teamReviewDataSource.getTeamRetroList(projectId, startDate, endDate)
    }.onSuccess {
        Log.d("teamRetro: ", "팀원 회고 현황 조회 성공")
    }.onFailure {
        Log.d("teamRetro: ", "팀원 회고 현황 조회 실패")
    }

    override suspend fun getTeamPuzzle(
        projectId: Int,
        today: String,
    ): Result<ResponseTeamPuzzleBoardDto> = runCatching {
        teamReviewDataSource.getTeamPuzzle(projectId, today)
    }

    override suspend fun getTeamPuzzleBoard(
        projectId: Int,
        today: String,
    ): Result<List<TeamPuzzleBoard>> = runCatching {
        teamReviewDataSource.getTeamPuzzle(projectId, today).data.toPuzzleBoard()
    }

    override suspend fun getTeamRanking(projectId: Int): Result<List<TeamRanking>> =
        runCatching {
            teamReviewDataSource.getTeamRanking(projectId).toTeamRanking()
        }
}
