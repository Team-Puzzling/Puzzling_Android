package com.puzzling.puzzlingaos.data.repository

import android.util.Log
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamRetroListDto
import com.puzzling.puzzlingaos.data.source.remote.TeamRetroDataSource
import com.puzzling.puzzlingaos.domain.repository.TeamRetroRepository
import javax.inject.Inject

class TeamRetroRepositoryImpl @Inject constructor(
    private val teamRetroDataSource: TeamRetroDataSource,
) : TeamRetroRepository {
    override suspend fun getTeamRetroList(
        projectId: Int,
        startDate: String,
        endDate: String,
    ): Result<ResponseTeamRetroListDto> = runCatching {
        teamRetroDataSource.getTeamRetroList(projectId, startDate, endDate)
    }.onSuccess {
        Log.d("teamRetro: ", "팀원 회고 현황 조회 성공")
    }.onFailure {
        Log.d("teamRetro: ", "팀원 회고 현황 조회 실패")
    }
}
