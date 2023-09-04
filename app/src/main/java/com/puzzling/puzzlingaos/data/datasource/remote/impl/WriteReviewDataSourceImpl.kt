package com.puzzling.puzzlingaos.data.datasource.remote.impl

import com.puzzling.puzzlingaos.data.datasource.remote.WriteReviewDataSource
import com.puzzling.puzzlingaos.data.model.request.RequestReview5FDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewAARDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewTILDto
import com.puzzling.puzzlingaos.data.model.response.ResponsePreviousTemplateDto
import com.puzzling.puzzlingaos.data.model.response.ResponseReviewTypeDto
import com.puzzling.puzzlingaos.data.model.response.ResponseSaveReviewDto
import com.puzzling.puzzlingaos.data.service.WriteReviewService
import javax.inject.Inject

class WriteReviewDataSourceImpl @Inject constructor(
    private val apiService: WriteReviewService,
) : WriteReviewDataSource {
    override suspend fun getReviewTemplate(): ResponseReviewTypeDto =
        apiService.getReviewTemplate()

    override suspend fun uploadReviewTIL(
        memberId: Int,
        projectId: Int,
        requestReviewTIL: RequestReviewTILDto,
    ): ResponseSaveReviewDto =
        apiService.postSaveReviewTIL(memberId, projectId, requestReviewTIL)

    override suspend fun uploadReview5F(
        memberId: Int,
        projectId: Int,
        requestReview5F: RequestReview5FDto,
    ): ResponseSaveReviewDto =
        apiService.postSaveReview5F(memberId, projectId, requestReview5F)

    override suspend fun uploadReviewAAR(
        memberId: Int,
        projectId: Int,
        requestReviewAAR: RequestReviewAARDto,
    ): ResponseSaveReviewDto =
        apiService.postSaveReviewAAR(memberId, projectId, requestReviewAAR)

    override suspend fun getPreviousTemplate(
        memberId: Int,
        projectId: Int,
    ): ResponsePreviousTemplateDto = apiService.getPreviousTemplate(
        memberId,
        projectId,
    )
}
