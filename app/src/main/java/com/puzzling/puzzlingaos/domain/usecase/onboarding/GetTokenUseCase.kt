package com.puzzling.puzzlingaos.domain.usecase.onboarding

import com.puzzling.puzzlingaos.domain.repository.TokenRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetTokenUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
) {

    suspend operator fun invoke() = tokenRepository.getToken()
}
