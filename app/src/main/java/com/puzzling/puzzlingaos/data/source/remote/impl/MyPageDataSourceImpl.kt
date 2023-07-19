package com.puzzling.puzzlingaos.data.source.remote.impl

import com.puzzling.puzzlingaos.data.model.response.ResponseDetailRetroDto
import com.puzzling.puzzlingaos.data.model.response.ResponseMyRetroListDto
import com.puzzling.puzzlingaos.data.service.MyPageService
import com.puzzling.puzzlingaos.data.source.remote.MyPageDataSource
import javax.inject.Inject

class MyPageDataSourceImpl @Inject constructor(private val apiService: MyPageService) :
    MyPageDataSource {
    override suspend fun getMyProjectReview(
        memberId: Int,
        projectId: Int,
    ): ResponseMyRetroListDto = apiService.getMyProjectReview(memberId, projectId)
    override suspend fun getMyDetailReview(
        memberId: Int,
        projectId: Int,
        startDate: String,
        endDate: String,
    ): ResponseDetailRetroDto =
        apiService.getMyDetailReview(memberId, projectId, startDate, endDate)
}
