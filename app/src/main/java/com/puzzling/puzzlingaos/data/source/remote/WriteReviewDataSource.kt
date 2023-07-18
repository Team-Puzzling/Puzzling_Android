package com.puzzling.puzzlingaos.data.source.remote

import com.puzzling.puzzlingaos.data.model.request.RequestReview5FDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewAARDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewTILDto
import com.puzzling.puzzlingaos.data.model.response.ResponsePreviousTemplateDto
import com.puzzling.puzzlingaos.data.model.response.ResponseReviewTypeDto
import com.puzzling.puzzlingaos.data.model.response.ResponseSaveReviewDto

interface WriteReviewDataSource {
    suspend fun getReviewTemplate(): ResponseReviewTypeDto

    suspend fun uploadReviewTIL(
        memberId: Int,
        projectId: Int,
        requestReviewTIL: RequestReviewTILDto,
    ): ResponseSaveReviewDto

    suspend fun uploadReview5F(
        memberId: Int,
        projectId: Int,
        requestReview5F: RequestReview5FDto,
    ): ResponseSaveReviewDto

    suspend fun uploadReviewAAR(
        memberId: Int,
        projectId: Int,
        requestReviewAAR: RequestReviewAARDto,
    ): ResponseSaveReviewDto

    suspend fun getPreviousTemplate(
        memberId: Int,
        projectId: Int,
    ): ResponsePreviousTemplateDto
}
