package com.puzzling.puzzlingaos.data.source.remote

import com.puzzling.puzzlingaos.data.model.request.ExampleRequest
import com.puzzling.puzzlingaos.data.model.response.ExampleResponse
import com.puzzling.puzzlingaos.data.service.ExampleService

class ExampleRemoteDataSource(
    private val exampleService: ExampleService
) {
    suspend fun postExample(exampleRequestDto: ExampleRequest): ExampleResponse =
        exampleService.postExample(exampleRequestDto)
}
