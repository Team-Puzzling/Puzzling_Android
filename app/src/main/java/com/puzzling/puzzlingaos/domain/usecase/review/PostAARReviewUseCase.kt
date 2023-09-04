package com.puzzling.puzzlingaos.domain.usecase.review

import com.puzzling.puzzlingaos.data.model.request.RequestReviewAARDto
import com.puzzling.puzzlingaos.domain.repository.WriteReviewRepository
import javax.inject.Inject

class PostAARReviewUseCase @Inject constructor(private val repository: WriteReviewRepository) {
    suspend operator fun invoke(
        memberId: Int,
        projectId: Int,
        requestReviewAARDto: RequestReviewAARDto,
    ) = repository.uploadAAR(memberId, projectId, requestReviewAARDto)
}
