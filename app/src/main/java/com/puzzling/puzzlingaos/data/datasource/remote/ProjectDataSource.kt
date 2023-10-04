package com.puzzling.puzzlingaos.data.datasource.remote

import com.puzzling.puzzlingaos.data.model.request.RequestJoinProject
import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseInvitationCodeDto
import com.puzzling.puzzlingaos.data.model.response.ResponseJoinProjectDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRetroWeekDto

interface ProjectDataSource {
    suspend fun projectRegister(
        memberId: Int,
        request: RequestProjectRegisterDto,
    ): ResponseProjectRegisterDto

    suspend fun joinProject(
        memberId: Int,
        request: RequestJoinProject,
    ): ResponseJoinProjectDto

    suspend fun isValidInvitationCode(
        invitationCode: String,
    ): ResponseInvitationCodeDto

    suspend fun getProjectWeekCycle(
        projectId: Int,
    ): ResponseProjectRetroWeekDto
}
