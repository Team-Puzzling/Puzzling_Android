package com.puzzling.puzzlingaos.data.service

import com.puzzling.puzzlingaos.data.model.request.ExampleRequest
import com.puzzling.puzzlingaos.data.model.response.ExampleResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ExampleService {
    // 예시 API
    @POST("api/example")
    suspend fun postExample(
        @Body request: ExampleRequest
    ): ExampleResponse
}
