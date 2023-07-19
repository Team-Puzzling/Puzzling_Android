package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.ResponseInvitationCodeDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ProjectService {

    @POST("api/v1/member/{memberId}/project")
    suspend fun projectRegister2(
        @Path("memberId") memberId: Int,
        @Body request: RequestProjectRegisterDto,
    ): ResponseProjectRegisterDto

    @GET("api/v1/project/verify?")
    suspend fun isValidInvitationCode(
        @Query("invitationCode") invitationCode: String,
    ) : ResponseInvitationCodeDto
}
