package com.puzzling.puzzlingaos.data.source.remote

import com.puzzling.puzzlingaos.data.model.response.ResponseTeamReviewListDto

interface TeamReviewDataSource {

    suspend fun getTeamRetroList(
        projectId: Int,
        startDate: String,
        endDate: String,
    ): ResponseTeamReviewListDto
}
