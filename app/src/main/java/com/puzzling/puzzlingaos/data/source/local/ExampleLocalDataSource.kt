package com.puzzling.puzzlingaos.data.source.local

import com.puzzling.puzzlingaos.data.model.request.ExampleRequest
import com.puzzling.puzzlingaos.data.model.response.ExampleResponse
import com.puzzling.puzzlingaos.data.service.ExampleService

class ExampleLocalDataSource(
    private val exampleService: ExampleService,
) {
    suspend fun postExample(exampleRequestDto: ExampleRequest): ExampleResponse =
        exampleService.postExample(exampleRequestDto)
}
