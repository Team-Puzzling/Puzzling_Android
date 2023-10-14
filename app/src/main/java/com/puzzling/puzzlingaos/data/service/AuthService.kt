package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.request.RequestLoginDto
import com.puzzling.puzzlingaos.data.model.response.ResponseLoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/auth")
    suspend fun login(
        @Body socialPlatform: RequestLoginDto,
    ): ResponseLoginDto
}
