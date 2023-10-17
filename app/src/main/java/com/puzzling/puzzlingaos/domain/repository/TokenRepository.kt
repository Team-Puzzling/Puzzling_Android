package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.entity.Token

interface TokenRepository {
    suspend fun setToken(accessToken: String)

    suspend fun getToken(): Token
}
