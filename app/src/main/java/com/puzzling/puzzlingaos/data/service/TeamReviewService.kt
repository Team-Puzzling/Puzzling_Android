package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.response.ResponseTeamPuzzleBoardDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamRankingDto
import com.puzzling.puzzlingaos.data.model.response.ResponseTeamReviewListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamReviewService {

    // api/v1/project/{projectId}/team/review?startDate={}&endDate={}
    @GET("api/v1/project/{projectId}/team/review?")
    suspend fun getTeamRetroList(
        @Path("projectId") memberId: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
    ): ResponseTeamReviewListDto

    /*
    [팀 대시보드 퍼즐 조회 API]
    ~/api/v1/project/{projectId}/team/puzzle?today={}
     */
    @GET("api/v1/project/{projectId}/team/puzzle?")
    suspend fun getTeamPuzzleBoard(
        @Path("projectId") projectId: Int,
        @Query("today") today: String,
    ): ResponseTeamPuzzleBoardDto

    /*
    [팀원 회고 랭킹 조회 API]
    ~/api/v1/project/{projectId}/rank
     */

    @GET("api/v1/project/{projectId}/rank")
    suspend fun getTeamRanking(
        @Path("projectId") projectId: Int,
    ): ResponseTeamRankingDto
}
