package com.puzzling.puzzlingaos.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
//    @Singleton
//    @Binds
//    abstract fun providesBookMarkRepository(repoImpl: BookMarkRepositoryImpl): BookMarkRepository
}
