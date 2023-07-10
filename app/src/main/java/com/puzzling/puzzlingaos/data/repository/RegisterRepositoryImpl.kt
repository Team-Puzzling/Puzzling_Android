package com.puzzling.puzzlingaos.data.repository

import com.puzzling.puzzlingaos.data.source.remote.RegisterRemoteDataSource
import com.puzzling.puzzlingaos.domain.repository.RegisterRepository

class RegisterRepositoryImpl(private val registerRemoteDataSource: RegisterRemoteDataSource) : RegisterRepository {

    override suspend fun register(): Result<Unit> =
        kotlin.runCatching {
        }
}
