package com.puzzling.puzzlingaos.data.datasource.remote.impl

import com.puzzling.puzzlingaos.data.datasource.remote.ProjectDataSource
import com.puzzling.puzzlingaos.data.model.request.RequestInvitationCode
import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseInvitationCodeDto
import com.puzzling.puzzlingaos.data.model.response.ResponseJoinProjectDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRetroWeekDto
import com.puzzling.puzzlingaos.data.service.ProjectService
import javax.inject.Inject

class ProjectDataSourceImpl @Inject constructor(
    private val apiService: ProjectService,
) : ProjectDataSource {

    override suspend fun projectRegister(
        memberId: Int,
        request: RequestProjectRegisterDto,
    ): ResponseProjectRegisterDto =
        apiService.projectRegister2(memberId, request)

    override suspend fun joinProject(
        memberId: Int,
        request: RequestInvitationCode,
    ): ResponseJoinProjectDto = apiService.joinProject(memberId, request)

    override suspend fun isValidInvitationCode(invitationCode: String): ResponseInvitationCodeDto =
        apiService.isValidInvitationCode(invitationCode)

    override suspend fun getProjectWeekCycle(projectId: Int): ResponseProjectRetroWeekDto =
        apiService.getProjectWeekCycle(projectId)
}
