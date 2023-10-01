package com.puzzling.puzzlingaos.di

import com.puzzling.puzzlingaos.data.repository.*
import com.puzzling.puzzlingaos.domain.repository.*
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

    @Singleton
    @Binds
    abstract fun providesTokenRepository(repoImpl: TokenRepositoryImpl): TokenRepository

    @Singleton
    @Binds
    abstract fun providesAuthRepository(repoImpl: AuthRepositoryImpl): AuthRepository
}
