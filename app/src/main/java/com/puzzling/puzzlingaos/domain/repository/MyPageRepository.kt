package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.domain.entity.DetailRetro
import com.puzzling.puzzlingaos.domain.entity.ProjectReview

interface MyPageRepository {

    suspend fun getMyProjectReview(
        memberId: Int,
        projectId: Int,
    ): Result<List<ProjectReview>>

    suspend fun getMyDetailReview(
        memberId: Int,
        projectId: Int,
        startDate: String,
        endDate: String,
    ): Result<List<DetailRetro>>
}
