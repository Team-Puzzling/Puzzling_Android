package com.puzzling.puzzlingaos.data.source.remote.impl

import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto
import com.puzzling.puzzlingaos.data.service.ProjectService
import com.puzzling.puzzlingaos.data.source.remote.ProjectDataSource
import javax.inject.Inject

class ProjectDataSourceImpl @Inject constructor(
    private val apiService: ProjectService,
) : ProjectDataSource {

    override suspend fun projectRegister(
        memberId: Int,
        request: RequestProjectRegisterDto,
    ): ResponseProjectRegisterDto =
        apiService.projectRegister(memberId, request).data!!
}
