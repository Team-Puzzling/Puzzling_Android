package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.model.response.ResponseTeamRetroListDto

interface TeamRetroRepository {

    suspend fun getTeamRetroList(
        projectId: Int,
        startDate: String,
        endDate: String,
    ): Result<ResponseTeamRetroListDto>
}