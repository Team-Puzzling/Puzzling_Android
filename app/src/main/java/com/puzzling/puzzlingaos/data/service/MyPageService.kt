package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.response.ResponseDetailRetroDto
import com.puzzling.puzzlingaos.data.model.response.ResponseMyRetroListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyPageService {
    @GET("api/v1/member/{memberId}/project/{projectId}/reviews")
    suspend fun getMyProjectReview(
        @Path("memberId") memberId: Int,
        @Path("projectId") projectId: Int,
    ): ResponseMyRetroListDto

    @GET("api/v1/member/{memberId}/project/{projectId}/review?")
    suspend fun getMyDetailReview(
        @Path("memberId") memberId: Int,
        @Path("projectId") projectId: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
    ): ResponseDetailRetroDto
}
