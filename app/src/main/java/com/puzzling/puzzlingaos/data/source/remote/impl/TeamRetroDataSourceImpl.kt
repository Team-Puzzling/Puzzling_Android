package com.puzzling.puzzlingaos.data.source.remote.impl

import com.puzzling.puzzlingaos.data.model.response.ResponseTeamRetroListDto
import com.puzzling.puzzlingaos.data.service.TeamRetroService
import com.puzzling.puzzlingaos.data.source.remote.TeamRetroDataSource
import javax.inject.Inject

class TeamRetroDataSourceImpl @Inject constructor(
    private val apiService: TeamRetroService
): TeamRetroDataSource {
    override suspend fun getTeamRetroList(
        projectId: Int,
        startDate: String,
        endDate: String
    ): ResponseTeamRetroListDto =
        apiService.getTeamRetroList(projectId, startDate, endDate)
}