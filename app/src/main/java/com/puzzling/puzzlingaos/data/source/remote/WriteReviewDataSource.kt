package com.puzzling.puzzlingaos.data.source.remote

import com.puzzling.puzzlingaos.data.model.request.RequestReview5FDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewAARDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewTILDto
import com.puzzling.puzzlingaos.data.model.response.ResponsePreviousTemplateDto
import com.puzzling.puzzlingaos.data.model.response.ResponseReviewTypeDto

interface WriteReviewDataSource {
    suspend fun getReviewTemplate(): ResponseReviewTypeDto

    suspend fun uploadReviewTIL(
        memberId: Int,
        projectId: Int,
        requestReviewTIL: RequestReviewTILDto,
    ): RequestReviewTILDto

    suspend fun uploadReview5F(
        memberId: Int,
        projectId: Int,
        requestReview5F: RequestReview5FDto,
    ): RequestReview5FDto

    suspend fun uploadReviewAAR(
        memberId: Int,
        projectId: Int,
        requestReviewAAR: RequestReviewAARDto,
    ): RequestReviewAARDto

    suspend fun getPreviousTemplate(
        memberId: Int,
        projectId: Int,
    ): ResponsePreviousTemplateDto
}
