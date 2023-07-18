package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.response.BaseResponse
import com.puzzling.puzzlingaos.data.model.response.ResponseActionPlanDto
import com.puzzling.puzzlingaos.data.model.response.ResponseMyPuzzleBoardDto
import com.puzzling.puzzlingaos.data.model.response.ResponseProceedingProjectDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/*
개인 회고
 */
interface PersonalReviewService {
    /*
    [개인 대시보드 퍼즐 조회 API]
    ~/api/v1/member/3/project/4/puzzle?today=2023-07-05
     */
    @GET("api/v1/member/{memberId}/project/{projectId}/puzzle?today={today}")
    suspend fun getMyPuzzleBoard(
        @Path("memberId") memberId: Int,
        @Path("projectId") projectId: Int,
        @Query("today") today: String,
    ): BaseResponse<ResponseMyPuzzleBoardDto>

    /*
    [ACTIONPLAN 내용 조회 API]
    ~/api/v1/member/{memberId}/project/{projectId}/actionplan
     */
    @GET("api/v1/member/{memberId}/project/{projectId}/actionplan")
    suspend fun getMyActionPlan(
        @Path("memberId") memberId: Int,
        @Path("projectId") projectId: Int,
    ): BaseResponse<ResponseActionPlanDto>

    /*
    [진행 중인 프로젝트 리스트 조회 API]
    ~/api/v1/member/{memberId}/project/all
     */
    @GET("api/v1/member/{memberId}/project/all")
    suspend fun getProceedingProjects(
        @Path("memberId") memberId: Int,
    ): BaseResponse<ResponseProceedingProjectDto>
}
