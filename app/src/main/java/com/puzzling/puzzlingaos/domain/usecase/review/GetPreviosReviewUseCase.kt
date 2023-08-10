package com.puzzling.puzzlingaos.domain.usecase.review

import com.puzzling.puzzlingaos.domain.repository.WriteReviewRepository
import javax.inject.Inject

class GetPreviosReviewUseCase @Inject constructor(private val repository: WriteReviewRepository) {
    suspend operator fun invoke(memberId: Int, projectId: Int) =
        repository.getPreviousTemplate(memberId, projectId)
}
