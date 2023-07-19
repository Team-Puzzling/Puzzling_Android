package com.puzzling.puzzlingaos.data.repository

import android.util.Log
import com.puzzling.puzzlingaos.data.model.request.RequestInvitationCode
import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseInvitationCodeDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRetroWeekDto
import com.puzzling.puzzlingaos.data.source.remote.ProjectDataSource
import com.puzzling.puzzlingaos.domain.repository.ProjectRepository
import javax.inject.Inject

class ProjectRepositoryImpl @Inject constructor(
    private val projectDataSource: ProjectDataSource,
) : ProjectRepository {

    override suspend fun projectRegister(
        memberId: Int,
        request: RequestProjectRegisterDto,
    ): Result<ResponseProjectRegisterDto> = runCatching {
        projectDataSource.projectRegister(memberId, request)
    }.onSuccess {
        Log.d("register: ", "등록 성공")
    }.onFailure {
        Log.d("register: ", "등록 실패")
    }

    override suspend fun joinProject(
        memberId: Int,
        request: RequestInvitationCode
    ): ResponseInvitationCodeDto {
        return projectDataSource.joinProject(memberId, request)
    }

    override suspend fun isValidInvitationCode(invitationCode: String): ResponseInvitationCodeDto {
        return projectDataSource.isValidInvitationCode(invitationCode)
    }

    override suspend fun getProjectWeekCycle(projectId: Int): ResponseProjectRetroWeekDto {
        return projectDataSource.getProjectWeekCycle(projectId)
    }
}
