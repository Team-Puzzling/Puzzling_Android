package com.puzzling.puzzlingaos.domain.usecase.onboarding

import com.puzzling.puzzlingaos.domain.repository.TokenRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostLocalTokenUseCase @Inject constructor(private val repository: TokenRepository) {
    suspend operator fun invoke(token: String) = repository.setToken(token)
}
