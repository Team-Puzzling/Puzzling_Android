package com.puzzling.puzzlingaos.domain.usecase.review

import com.puzzling.puzzlingaos.data.model.request.RequestReview5FDto
import com.puzzling.puzzlingaos.domain.repository.WriteReviewRepository
import javax.inject.Inject

class Post5FReviewUseCase @Inject constructor(private val repository: WriteReviewRepository) {
    suspend operator fun invoke(
        memberId: Int,
        projectId: Int,
        requestReview5F: RequestReview5FDto,
    ) = repository.upload5F(memberId, projectId, requestReview5F)
}
