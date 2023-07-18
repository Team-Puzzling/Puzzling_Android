package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.model.response.ResponseDetailRetroDto
import com.puzzling.puzzlingaos.data.model.response.ResponseMyRetroListDto

interface MyPageRepository {

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
