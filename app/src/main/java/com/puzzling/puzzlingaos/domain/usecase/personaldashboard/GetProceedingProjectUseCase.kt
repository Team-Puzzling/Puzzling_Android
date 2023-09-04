package com.puzzling.puzzlingaos.domain.usecase.personaldashboard

import com.puzzling.puzzlingaos.domain.repository.MyBoardRepository
import javax.inject.Inject

class GetProceedingProjectUseCase @Inject constructor(private val repository: MyBoardRepository) {
    suspend operator fun invoke(memberId: Int) = repository.getProceedingProject(memberId)
}
