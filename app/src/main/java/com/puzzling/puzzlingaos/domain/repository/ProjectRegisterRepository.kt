package com.puzzling.puzzlingaos.domain.repository

interface ProjectRegisterRepository {

    suspend fun register(): Result<Unit>
}
