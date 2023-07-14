package com.puzzling.puzzlingaos.domain.repository

interface RegisterRepository {

    suspend fun register(): Result<Unit>
}
