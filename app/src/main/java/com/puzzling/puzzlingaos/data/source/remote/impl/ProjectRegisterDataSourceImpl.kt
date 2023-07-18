package com.puzzling.puzzlingaos.data.source.remote.impl

import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto
import com.puzzling.puzzlingaos.data.service.RegisterService
import com.puzzling.puzzlingaos.data.source.remote.ProjectRegisterDataSource
import javax.inject.Inject

class ProjectRegisterDataSourceImpl @Inject constructor(
    private val apiService: RegisterService,
) : ProjectRegisterDataSource {

    override suspend fun projectRegister(
        memberId: Int,
        request: RequestProjectRegisterDto,
    ): ResponseProjectRegisterDto =
        apiService.projectRegister(memberId, request).data!!
}
