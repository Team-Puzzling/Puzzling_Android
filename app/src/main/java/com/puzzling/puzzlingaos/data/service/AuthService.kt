package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.request.RequestLoginDto
import com.puzzling.puzzlingaos.data.model.response.ResponseLoginDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTokenDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("api/v1/auth")
    suspend fun login(
        @Body socialPlatform: RequestLoginDto,
    ): ResponseLoginDto

    @GET("api/v1/auth/token")
    suspend fun getToken(): ResponseTokenDto
}
