package com.puzzling.puzzlingaos.di

import com.puzzling.puzzlingaos.data.source.remote.MyDashBoardDataSource
import com.puzzling.puzzlingaos.data.source.remote.ProjectDataSource
import com.puzzling.puzzlingaos.data.source.remote.TeamRetroDataSource
import com.puzzling.puzzlingaos.data.source.remote.WriteReviewDataSource
import com.puzzling.puzzlingaos.data.source.remote.impl.MyDashBoardDataSourceImpl
import com.puzzling.puzzlingaos.data.source.remote.impl.ProjectDataSourceImpl
import com.puzzling.puzzlingaos.data.source.remote.impl.TeamRetroDataSourceImpl
import com.puzzling.puzzlingaos.data.source.remote.impl.WriteReviewDataSourceImpl
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
    abstract fun providesWriteReviewDataSource(DataSourceImpl: WriteReviewDataSourceImpl): WriteReviewDataSource

    @Singleton
    @Binds
    abstract fun sendProjectRegisterDataSource(DataSourceImpl: ProjectDataSourceImpl): ProjectDataSource

    @Singleton
    @Binds
    abstract fun provideTeamRetroDataSource(DataSourceImpl: TeamRetroDataSourceImpl): TeamRetroDataSource
}
