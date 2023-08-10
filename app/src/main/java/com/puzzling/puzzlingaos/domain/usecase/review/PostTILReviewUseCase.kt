package com.puzzling.puzzlingaos.domain.usecase.review

import com.puzzling.puzzlingaos.data.model.request.RequestReviewTILDto
import com.puzzling.puzzlingaos.domain.repository.WriteReviewRepository
import javax.inject.Inject

class PostTILReviewUseCase @Inject constructor(private val repository: WriteReviewRepository) {
    suspend operator fun invoke(
        memberId: Int,
        projectId: Int,
        requestReviewTILDto: RequestReviewTILDto,
    ) = repository.uploadTIL(memberId, projectId, requestReviewTILDto)
}
