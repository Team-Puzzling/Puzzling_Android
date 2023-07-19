package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.response.ResponseTeamRetroListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TeamRetroService {

    // api/v1/project/{projectId}/team/review?startDate={}&endDate={}
    @GET("api/v1/project/{projectId}/team/review?")
    suspend fun getTeamRetroList(
        @Path("projectId") memberId: Int,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
    ): ResponseTeamRetroListDto
}
