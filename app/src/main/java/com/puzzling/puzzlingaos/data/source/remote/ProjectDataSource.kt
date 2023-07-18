package com.puzzling.puzzlingaos.data.source.remote

import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto

interface ProjectDataSource {
    suspend fun projectRegister(
        memberId: Int,
        request: RequestProjectRegisterDto,
    ): ResponseProjectRegisterDto
}
