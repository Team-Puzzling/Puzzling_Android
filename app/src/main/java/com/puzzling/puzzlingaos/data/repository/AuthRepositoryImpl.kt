package com.puzzling.puzzlingaos.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.puzzling.puzzlingaos.data.datasource.remote.AuthDataSource
import com.puzzling.puzzlingaos.data.entity.Token
import com.puzzling.puzzlingaos.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val tokenDataStore: DataStore<Token>,
    private val authDataSource: AuthDataSource,
) : AuthRepository {
    override suspend fun login(socialPlatform: String): Result<Token> = runCatching {
        authDataSource.login(socialPlatform).getToken()
    }.onSuccess { token ->
        tokenDataStore.updateData { Token(token.accessToken, token.refreshToken) }
        Log.d("AuthRepoImpl", "${tokenDataStore.data.first()}")
    }.onFailure {
        Log.d("AuthRepoImpl", "$it 에러")
    }

    override suspend fun getToken(): Result<Token> = runCatching {
        authDataSource.getToken().getToken()
    }.onSuccess { token ->
        tokenDataStore.updateData { Token(token.accessToken, token.refreshToken) }
        Log.d("AuthRepoImpl", "${tokenDataStore.data.first()}")
    }.onFailure {
        Log.d("AuthRepoImpl", "$it 에러")
    }
}
