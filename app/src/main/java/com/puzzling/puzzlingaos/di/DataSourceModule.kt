package com.puzzling.puzzlingaos.di

import com.puzzling.puzzlingaos.data.source.remote.MyDashBoardDataSource
import com.puzzling.puzzlingaos.data.source.remote.ProjectDataSource
import com.puzzling.puzzlingaos.data.source.remote.TeamReviewDataSource
import com.puzzling.puzzlingaos.data.source.remote.WriteReviewDataSource
import com.puzzling.puzzlingaos.data.source.remote.impl.MyDashBoardDataSourceImpl
import com.puzzling.puzzlingaos.data.source.remote.impl.ProjectDataSourceImpl
import com.puzzling.puzzlingaos.data.source.remote.impl.TeamReviewDataSourceImpl
import com.puzzling.puzzlingaos.data.source.remote.MyPageDataSource
import com.puzzling.puzzlingaos.data.source.remote.impl.WriteReviewDataSourceImpl
import com.puzzling.puzzlingaos.data.source.remote.impl.MyPageDataSourceImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun providesPersonalReviewDataSource(DataSourceImpl: MyDashBoardDataSourceImpl): MyDashBoardDataSource

    @Singleton
    @Binds
    abstract fun providesMyPageDataSource(DataSourceImpl: MyPageDataSourceImpl): MyPageDataSource

    @Singleton
    @Binds
    abstract fun providesProjectRegisterDataSource(DataSourceImpl: ProjectDataSourceImpl): ProjectDataSource

    @Singleton
    @Binds
    abstract fun sendProjectRegisterDataSource(DataSourceImpl: ProjectDataSourceImpl): ProjectDataSource

    @Singleton
    @Binds
    abstract fun provideTeamRetroDataSource(DataSourceImpl: TeamReviewDataSourceImpl): TeamReviewDataSource

    @Singleton
    @Binds
    abstract fun providesWriteReviewDataSource(DataSourceImpl: WriteReviewDataSourceImpl): WriteReviewDataSource
}
