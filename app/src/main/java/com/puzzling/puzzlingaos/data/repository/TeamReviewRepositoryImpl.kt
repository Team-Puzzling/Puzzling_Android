package com.puzzling.puzzlingaos.data.repository

import android.util.Log
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamReviewListDto
import com.puzzling.puzzlingaos.data.source.remote.TeamReviewDataSource
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
}
