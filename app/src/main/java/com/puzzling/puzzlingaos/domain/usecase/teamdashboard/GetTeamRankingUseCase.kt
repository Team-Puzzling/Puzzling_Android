package com.puzzling.puzzlingaos.domain.usecase.teamdashboard

import com.puzzling.puzzlingaos.domain.repository.TeamDashBoardRepository
import javax.inject.Inject

class GetTeamRankingUseCase @Inject constructor(private val repository: TeamDashBoardRepository) {
    suspend operator fun invoke(projectId: Int) =
        repository.getTeamRanking(projectId)
}
