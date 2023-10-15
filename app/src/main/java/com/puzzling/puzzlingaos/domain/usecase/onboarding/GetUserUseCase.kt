package com.puzzling.puzzlingaos.domain.usecase.onboarding

import com.puzzling.puzzlingaos.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke() = repository.getUserInfo()
}
