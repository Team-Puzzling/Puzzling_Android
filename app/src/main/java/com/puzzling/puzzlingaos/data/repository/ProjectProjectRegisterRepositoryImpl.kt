package com.puzzling.puzzlingaos.data.repository

import com.puzzling.puzzlingaos.data.source.remote.ProjectRegisterDataSource
import com.puzzling.puzzlingaos.domain.repository.ProjectRegisterRepository

class ProjectProjectRegisterRepositoryImpl(private val projectRegisterDataSource: ProjectRegisterDataSource) : ProjectRegisterRepository {

    override suspend fun register(): Result<Unit> =
        kotlin.runCatching {
        }
}
