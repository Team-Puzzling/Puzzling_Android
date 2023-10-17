package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.response.ResponseTokenDto
import retrofit2.http.GET

interface ReIssueTokenService {
    @GET("api/v1/auth/token")
    suspend fun getToken(): ResponseTokenDto
}
