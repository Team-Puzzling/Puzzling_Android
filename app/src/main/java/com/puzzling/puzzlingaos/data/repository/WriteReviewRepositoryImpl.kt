package com.puzzling.puzzlingaos.data.repository

import com.puzzling.puzzlingaos.data.model.request.RequestReview5FDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewAARDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewTILDto
import com.puzzling.puzzlingaos.data.model.response.ResponsePreviousTemplateDto
import com.puzzling.puzzlingaos.data.model.response.ResponseSaveReviewDto
import com.puzzling.puzzlingaos.data.source.remote.WriteReviewDataSource
import com.puzzling.puzzlingaos.domain.entity.ReviewType
import com.puzzling.puzzlingaos.domain.repository.WriteReviewRepository
import javax.inject.Inject

class WriteReviewRepositoryImpl @Inject constructor(
    private val writeReviewDataSource: WriteReviewDataSource,
) : WriteReviewRepository {
    override suspend fun getReviewType(): Result<List<ReviewType>> = runCatching {
        writeReviewDataSource.getReviewTemplate().toReviewType()
    }

    override suspend fun uploadTIL(
        memberId: Int,
        projectId: Int,
        requestReviewTIL: RequestReviewTILDto,
    ): Result<ResponseSaveReviewDto> = runCatching {
        writeReviewDataSource.uploadReviewTIL(
            memberId,
            projectId,
            requestReviewTIL,
        )
    }

    override suspend fun upload5F(
        memberId: Int,
        projectId: Int,
        requestReview5F: RequestReview5FDto,
    ): Result<ResponseSaveReviewDto> = runCatching {
        writeReviewDataSource.uploadReview5F(
            memberId,
            projectId,
            requestReview5F,
        )
    }

    override suspend fun uploadAAR(
        memberId: Int,
        projectId: Int,
        requestReviewAAR: RequestReviewAARDto,
    ): Result<ResponseSaveReviewDto> = runCatching {
        writeReviewDataSource.uploadReviewAAR(
            memberId,
            projectId,
            requestReviewAAR,
        )
    }

    override suspend fun getPreviousTemplate(
        memberId: Int,
        projectId: Int,
    ): Result<ResponsePreviousTemplateDto> =
        runCatching {
            writeReviewDataSource.getPreviousTemplate(memberId, projectId)
        }
}
