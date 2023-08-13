package com.puzzling.puzzlingaos.domain.usecase.personaldashboard

import com.puzzling.puzzlingaos.domain.repository.MyBoardRepository
import javax.inject.Inject

class GetUserPuzzleUseCase @Inject constructor(private val repository: MyBoardRepository) {
    suspend operator fun invoke(memberId: Int, projectId: Int, today: String) =
        repository.getUserPuzzle(memberId, projectId, today)
}
