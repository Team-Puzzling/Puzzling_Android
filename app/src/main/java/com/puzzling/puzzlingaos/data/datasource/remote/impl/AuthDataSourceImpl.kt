package com.puzzling.puzzlingaos.data.datasource.remote.impl

import com.puzzling.puzzlingaos.data.datasource.remote.AuthDataSource
import com.puzzling.puzzlingaos.data.model.request.RequestLoginDto
import com.puzzling.puzzlingaos.data.model.response.ResponseLoginDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTokenDto
import com.puzzling.puzzlingaos.data.service.AuthService
import com.puzzling.puzzlingaos.data.service.ReIssueTokenService
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val authService: AuthService,
    private val reIssueTokenService: ReIssueTokenService,
) : AuthDataSource {

    override suspend fun login(socialPlatform: String): ResponseLoginDto =
        authService.login(RequestLoginDto(socialPlatform))

    override suspend fun getToken(): ResponseTokenDto =
        reIssueTokenService.getToken()
}
