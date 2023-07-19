package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.model.request.RequestReview5FDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewAARDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewTILDto
import com.puzzling.puzzlingaos.data.model.response.ResponsePreviousTemplateDto
import com.puzzling.puzzlingaos.data.model.response.ResponseSaveReviewDto
import com.puzzling.puzzlingaos.domain.entity.ReviewType

interface WriteReviewRepository {
    suspend fun getReviewType(): Result<List<ReviewType>>

    suspend fun uploadTIL(
        memberId: Int,
        projectId: Int,
        requestReviewTIL: RequestReviewTILDto,
    ): Result<ResponseSaveReviewDto>

    suspend fun upload5F(
        memberId: Int,
        projectId: Int,
        requestReview5F: RequestReview5FDto,
    ): Result<ResponseSaveReviewDto>

    suspend fun uploadAAR(
        memberId: Int,
        projectId: Int,
        requestReviewAAR: RequestReviewAARDto,
    ): Result<ResponseSaveReviewDto>

    suspend fun getPreviousTemplate(
        memberId: Int,
        projectId: Int,
    ): Result<ResponsePreviousTemplateDto>
}
