package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.request.RequestProjectRegisterDto
import com.puzzling.puzzlingaos.data.model.response.BaseResponse
import com.puzzling.puzzlingaos.data.model.response.ResponseProjectRegisterDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProjectService {

//    @POST("api/v1/member/{memberId}/project")
//    suspend fun projectRegister(
//        @Path("memberId") memberId: Int,
//        @Body request: RequestProjectRegisterDto,
//    ): BaseResponse<ResponseProjectRegisterDto>

    @POST("api/v1/member/{memberId}/project")
    suspend fun projectRegister2(
        @Path("memberId") memberId: Int,
        @Body request: RequestProjectRegisterDto,
    ): ResponseProjectRegisterDto
}
