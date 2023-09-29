package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.response.ResponseLoginDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTokenDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @POST("auth")
    suspend fun login(
        @Body socialPlatform: String,
    ): ResponseLoginDto

    @GET("auth/token")
    suspend fun getToken(): ResponseTokenDto
}
