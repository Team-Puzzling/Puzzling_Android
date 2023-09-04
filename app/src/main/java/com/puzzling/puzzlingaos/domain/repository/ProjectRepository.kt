package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.model.request.RequestInvitationCode
import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseJoinProjectDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRetroWeekDto
import com.puzzling.puzzlingaos.domain.entity.InvitationCode

interface ProjectRepository {

    // suspend fun register(): Result<Unit>

    suspend fun projectRegister(
        memberId: Int,
        request: RequestProjectRegisterDto,
    ): Result<ResponseProjectRegisterDto>

    suspend fun joinProject(
        memberId: Int,
        request: RequestInvitationCode,
    ): ResponseJoinProjectDto

    suspend fun isValidInvitationCode(
        invitationCode: String,
    ): Result<InvitationCode>

    suspend fun getProjectWeekCycle(
        projectId: Int,
    ): ResponseProjectRetroWeekDto
}
