package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.request.RequestReview5FDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewAARDto
import com.puzzling.puzzlingaos.data.model.request.RequestReviewTILDto
import com.puzzling.puzzlingaos.data.model.response.ResponsePreviousTemplateDto
import com.puzzling.puzzlingaos.data.model.response.ResponseReviewTypeDto
import com.puzzling.puzzlingaos.data.model.response.ResponseSaveReviewDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WriteReviewService {
    /*
    [회고 템플릿 목록 조회 API]
     ~/api/v1/review/template
     */
    @GET("api/v1/review/template")
    suspend fun getReviewTemplate(): ResponseReviewTypeDto

    /*
    [회고 글 작성 API(TIL)]
    ~/api/v1/member/{memberId}/project/{projectId}/review/TIL
    */
    @POST("api/v1/member/{memberId}/project/{projectId}/review/TIL")
    suspend fun postSaveReviewTIL(
        @Path("memberId") memberId: Int,
        @Path("projectId") projectId: Int,
        @Body reviewAnswer: RequestReviewTILDto,
    ): ResponseSaveReviewDto

    /*
   [회고 글 작성 API(5F)]
   ~/api/v1/member/{memberId}/project/{projectId}/review/5F
    */
    @POST("api/v1/member/{memberId}/project/{projectId}/review/5F")
    suspend fun postSaveReview5F(
        @Path("memberId") memberId: Int,
        @Path("projectId") projectId: Int,
        @Body reviewAnswer: RequestReview5FDto,
    ): ResponseSaveReviewDto

    /*
 [회고 글 작성 API(ARR)]
 ~/api/v1/member/{memberId}/project/{projectId}/review/ARR
  */
    @POST("api/v1/member/{memberId}/project/{projectId}/review/ARR")
    suspend fun postSaveReviewAAR(
        @Path("memberId") memberId: Int,
        @Path("projectId") projectId: Int,
        @Body reviewAnswer: RequestReviewAARDto,
    ): ResponseSaveReviewDto

    /*
   [이전 회고 템플릿 조회 API]
   ~/api/v1/member/{memberId}/project/{projectId}/review/previous-template
  ~/api/v1/member/2/project/4/review/previous-template
    */
    @GET("api/v1/member/{memberId}/project/{projectId}/review/previous-template")
    suspend fun getPreviousTemplate(
        @Path("memberId") memberId: Int,
        @Path("projectId") projectId: Int,
    ): ResponsePreviousTemplateDto
}
