package com.puzzling.puzzlingaos.data.datasource.remote

import com.puzzling.puzzlingaos.data.model.response.ResponseDetailRetroDto
import com.puzzling.puzzlingaos.data.model.response.ResponseMyRetroListDto

interface MyPageDataSource {
    suspend fun getMyProjectReview(
        memberId: Int,
        projectId: Int,
    ): ResponseMyRetroListDto

    suspend fun getMyDetailReview(
        memberId: Int,
        projectId: Int,
        startDate: String,
        endDate: String,
    ): ResponseDetailRetroDto
}
