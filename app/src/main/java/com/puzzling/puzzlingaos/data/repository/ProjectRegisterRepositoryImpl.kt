package com.puzzling.puzzlingaos.data.repository

import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto
import com.puzzling.puzzlingaos.data.source.remote.ProjectRegisterDataSource
import com.puzzling.puzzlingaos.domain.repository.ProjectRegisterRepository
import javax.inject.Inject

class ProjectRegisterRepositoryImpl @Inject constructor(
    private val projectRegisterDataSource: ProjectRegisterDataSource,
) : ProjectRegisterRepository {

//    override suspend fun register(): Result<Unit> =
//        kotlin.runCatching {
//        }

    override suspend fun projectRegister(
        memberId: Int,
        request: RequestProjectRegisterDto,
    ): Result<ResponseProjectRegisterDto> = runCatching {
        projectRegisterDataSource.projectRegister(memberId, request)
    }
}
