package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto

interface ProjectRegisterRepository {

    // suspend fun register(): Result<Unit>

    suspend fun projectRegister(
        memberId: Int,
        request: RequestProjectRegisterDto,
    ): Result<ResponseProjectRegisterDto>
}
