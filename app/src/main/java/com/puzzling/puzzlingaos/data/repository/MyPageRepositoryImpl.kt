package com.puzzling.puzzlingaos.data.repository

import com.puzzling.puzzlingaos.data.datasource.remote.MyPageDataSource
import com.puzzling.puzzlingaos.domain.entity.DetailRetro
import com.puzzling.puzzlingaos.domain.entity.ProjectReview
import com.puzzling.puzzlingaos.domain.repository.MyPageRepository
import javax.inject.Inject

class MyPageRepositoryImpl @Inject constructor(private val myPageDataSource: MyPageDataSource) :
    MyPageRepository {
    override suspend fun getMyProjectReview(
        memberId: Int,
        projectId: Int,
    ): Result<List<ProjectReview>> =
        runCatching {
            myPageDataSource.getMyProjectReview(memberId, projectId).toProjectReview()
        }

    override suspend fun getMyDetailReview(
        memberId: Int,
        projectId: Int,
        startDate: String,
        endDate: String,
    ): Result<List<DetailRetro>> = runCatching {
        myPageDataSource.getMyDetailReview(
            memberId,
            projectId,
            startDate,
            endDate,
        ).data.toDetailRetro()
    }
}
