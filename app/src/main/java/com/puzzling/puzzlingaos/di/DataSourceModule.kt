package com.puzzling.puzzlingaos.di

import com.puzzling.puzzlingaos.data.datasource.remote.AuthDataSource
import com.puzzling.puzzlingaos.data.datasource.remote.MyDashBoardDataSource
import com.puzzling.puzzlingaos.data.datasource.remote.MyPageDataSource
import com.puzzling.puzzlingaos.data.datasource.remote.ProjectDataSource
import com.puzzling.puzzlingaos.data.datasource.remote.TeamDashBoardDataSource
import com.puzzling.puzzlingaos.data.datasource.remote.WriteReviewDataSource
import com.puzzling.puzzlingaos.data.datasource.remote.impl.AuthDataSourceImpl
import com.puzzling.puzzlingaos.data.datasource.remote.impl.MyDashBoardDataSourceImpl
import com.puzzling.puzzlingaos.data.datasource.remote.impl.MyPageDataSourceImpl
import com.puzzling.puzzlingaos.data.datasource.remote.impl.ProjectDataSourceImpl
import com.puzzling.puzzlingaos.data.datasource.remote.impl.TeamReviewDataSourceImpl
import com.puzzling.puzzlingaos.data.datasource.remote.impl.WriteReviewDataSourceImpl
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
    abstract fun providesTeamRetroDataSource(DataSourceImpl: TeamReviewDataSourceImpl): TeamDashBoardDataSource

    @Singleton
    @Binds
    abstract fun providesWriteReviewDataSource(DataSourceImpl: WriteReviewDataSourceImpl): WriteReviewDataSource

    @Singleton
    @Binds
    abstract fun providesAuthDataSource(DataSourceImpl: AuthDataSourceImpl): AuthDataSource
}
