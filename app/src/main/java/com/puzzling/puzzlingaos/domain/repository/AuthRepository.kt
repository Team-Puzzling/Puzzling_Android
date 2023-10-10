package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.entity.Token
import com.puzzling.puzzlingaos.data.model.response.ResponseLoginDto

interface AuthRepository {

    suspend fun login(socialPlatform: String): Result<ResponseLoginDto>

    suspend fun getToken(): Result<Token>
}
