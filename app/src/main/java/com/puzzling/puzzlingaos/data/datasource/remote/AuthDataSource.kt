package com.puzzling.puzzlingaos.data.datasource.remote

import com.puzzling.puzzlingaos.data.model.response.ResponseLoginDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTokenDto

interface AuthDataSource {
    suspend fun login(socialPlatform: String): ResponseLoginDto

    suspend fun getToken(): ResponseTokenDto
}
