package com.puzzling.puzzlingaos.data.repository

import androidx.datastore.core.DataStore
import com.puzzling.puzzlingaos.data.entity.Token
import com.puzzling.puzzlingaos.domain.repository.TokenRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(private val dataStore: DataStore<Token>) :
    TokenRepository {
    override suspend fun setToken(token: String) {
        dataStore.updateData { Token(token) }
    }

    override suspend fun getToken(): Token = dataStore.data.first()
}
