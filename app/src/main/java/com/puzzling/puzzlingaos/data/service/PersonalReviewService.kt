package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.response.ResponseMyPuzzleBoardDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonalReviewService {
    /*
    [개인 대시보드 퍼즐 조회 API]
    api/v1/member/3/project/4/puzzle?today=2023-07-05
     */
    @GET("v1/member/{memberId}/project/{projectId}/puzzle?today={today}")
    suspend fun getMyPuzzleBoard(
        @Path("memberId") memberId: Int,
        @Path("projectId") projectId: Int,
        @Query("today") today: String,
    ): ResponseMyPuzzleBoardDto
}
