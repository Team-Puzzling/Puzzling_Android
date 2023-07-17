package com.puzzling.puzzlingaos.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

//    @Singleton
//    @Binds
//    abstract fun providesPersonalReviewDataSource(DataSourceImpl: PersonalReviewDataSourceImpl): PersonalReviewDataSource
}
