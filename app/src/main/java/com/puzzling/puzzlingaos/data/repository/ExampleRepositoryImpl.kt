package com.puzzling.puzzlingaos.data.repository

import com.puzzling.puzzlingaos.data.model.request.ExampleRequest
import com.puzzling.puzzlingaos.data.model.response.ExampleResponse
import com.puzzling.puzzlingaos.data.source.remote.ExampleRemoteDataSource
import com.puzzling.puzzlingaos.domain.repository.ExampleRepository

class ExampleRepositoryImpl(
    private val exampleDataSource: ExampleRemoteDataSource
) : ExampleRepository {
    override suspend fun postExample(exampleRequest: ExampleRequest): Result<ExampleResponse> =
        kotlin.runCatching { exampleDataSource.postExample(exampleRequest) }
}
