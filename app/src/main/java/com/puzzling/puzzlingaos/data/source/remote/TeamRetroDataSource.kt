package com.puzzling.puzzlingaos.data.source.remote

import com.puzzling.puzzlingaos.data.model.response.ResponseTeamRetroListDto

interface TeamRetroDataSource {

    suspend fun getTeamRetroList(
        projectId: Int,
        startDate: String,
        endDate: String,
    ): ResponseTeamRetroListDto
}
