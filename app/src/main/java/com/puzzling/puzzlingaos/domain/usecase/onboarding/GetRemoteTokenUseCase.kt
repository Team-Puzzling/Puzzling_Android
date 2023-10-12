package com.puzzling.puzzlingaos.domain.usecase.onboarding

import com.puzzling.puzzlingaos.domain.repository.AuthRepository
import javax.inject.Inject

class GetRemoteTokenUseCase @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke() = repository.getToken()
}
