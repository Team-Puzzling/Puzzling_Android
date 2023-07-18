package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.BaseResponse
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RegisterService {

    @POST("~/api/v1/member/{memberId}/project")
    suspend fun projectRegister(
        @Path("memberId") memberId: Int,
        @Query("request") request: RequestProjectRegisterDto,
    ): BaseResponse<ResponseProjectRegisterDto>
}
