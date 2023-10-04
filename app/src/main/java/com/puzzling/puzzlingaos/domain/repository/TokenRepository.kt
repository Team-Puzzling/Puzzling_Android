package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.entity.Token

interface TokenRepository {
    suspend fun setToken(token: String)

    suspend fun getToken(): Token
}
