package com.puzzling.puzzlingaos.domain.repository

import com.puzzling.puzzlingaos.data.model.request.ExampleRequest
import com.puzzling.puzzlingaos.data.model.response.ExampleResponse

interface ExampleRepository {
    suspend fun postExample(exampleRequest: ExampleRequest): Result<ExampleResponse>
}
