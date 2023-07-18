package com.puzzling.puzzlingaos.data.repository

import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto
import com.puzzling.puzzlingaos.data.source.remote.ProjectDataSource
import com.puzzling.puzzlingaos.domain.repository.ProjectRepository
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val projectDataSource: ProjectDataSource,
) : ProjectRepository {

//    override suspend fun register(): Result<Unit> =
//        kotlin.runCatching {
//        }

    override suspend fun projectRegister(
        memberId: Int,
        request: RequestProjectRegisterDto,
    ): Result<ResponseProjectRegisterDto> = runCatching {
        projectDataSource.projectRegister(memberId, request)
    }
}
