package com.puzzling.puzzlingaos.data.repository

import android.util.Log
import com.puzzling.puzzlingaos.data.datasource.remote.ProjectDataSource
import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.request.toRequestJoinProjectDto
import com.puzzling.puzzlingaos.data.model.response.ResponseJoinProjectDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto
import com.puzzling.puzzlingaos.domain.entity.InvitationCode
import com.puzzling.puzzlingaos.domain.entity.JoinProjectInfo
import com.puzzling.puzzlingaos.domain.entity.ReviewCycle
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
        request: JoinProjectInfo,
    ): Result<ResponseJoinProjectDto> = runCatching {
        projectDataSource.joinProject(memberId, request.toRequestJoinProjectDto())
    }

    override suspend fun isValidInvitationCode(invitationCode: String): Result<InvitationCode> =
        runCatching {
            projectDataSource.isValidInvitationCode(invitationCode).data.toInvitationCode()
        }

    override suspend fun getProjectWeekCycle(projectId: Int): Result<ReviewCycle> = runCatching {
        projectDataSource.getProjectWeekCycle(projectId).data.toReviewCycle()
    }
}
