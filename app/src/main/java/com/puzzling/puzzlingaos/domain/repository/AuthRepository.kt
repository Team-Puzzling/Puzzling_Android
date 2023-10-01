package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.entity.Token

interface AuthRepository {

    suspend fun login(socialPlatform: String): Result<Token>

    suspend fun getToken(): Result<Token>
}
