package com.puzzling.puzzlingaos.di

import com.puzzling.puzzlingaos.data.repository.MyBoardRepositoryImpl
import com.puzzling.puzzlingaos.data.repository.MyPageRepositoryImpl
import com.puzzling.puzzlingaos.data.repository.ProjectRepositoryImpl
import com.puzzling.puzzlingaos.data.repository.TeamDashBoardRepositoryImpl
import com.puzzling.puzzlingaos.data.repository.WriteReviewRepositoryImpl
import com.puzzling.puzzlingaos.domain.repository.MyBoardRepository
import com.puzzling.puzzlingaos.domain.repository.MyPageRepository
import com.puzzling.puzzlingaos.domain.repository.ProjectRepository
import com.puzzling.puzzlingaos.domain.repository.TeamDashBoardRepository
import com.puzzling.puzzlingaos.domain.repository.WriteReviewRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun providesMyBoardRepository(repoImpl: MyBoardRepositoryImpl): MyBoardRepository

    @Singleton
    @Binds
    abstract fun providesMyPageRepository(repoImpl: MyPageRepositoryImpl): MyPageRepository

    @Singleton
    @Binds
    abstract fun providesProjectRepository(repoImpl: ProjectRepositoryImpl): ProjectRepository

    @Singleton
    @Binds
    abstract fun provideTeamRetroRepository(repoImpl: TeamDashBoardRepositoryImpl): TeamDashBoardRepository

    @Singleton
    @Binds
    abstract fun providesWriteReviewRepository(repoImpl: WriteReviewRepositoryImpl): WriteReviewRepository
}
