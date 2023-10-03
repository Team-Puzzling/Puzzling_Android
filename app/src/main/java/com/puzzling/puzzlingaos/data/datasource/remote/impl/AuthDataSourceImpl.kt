package com.puzzling.puzzlingaos.data.datasource.remote.impl

import com.puzzling.puzzlingaos.data.datasource.remote.AuthDataSource
import com.puzzling.puzzlingaos.data.model.request.RequestLoginDto
import com.puzzling.puzzlingaos.data.model.response.ResponseLoginDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTokenDto
import com.puzzling.puzzlingaos.data.service.AuthService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val apiService: AuthService) : AuthDataSource {
    override suspend fun login(socialPlatform: String): ResponseLoginDto =
        apiService.login(RequestLoginDto(socialPlatform))

    override suspend fun getToken(): ResponseTokenDto = apiService.getToken()
}
