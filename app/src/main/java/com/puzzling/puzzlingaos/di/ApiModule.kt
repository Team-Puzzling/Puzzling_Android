package com.puzzling.puzzlingaos.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
//    @Provides
//    @Singleton
//    fun provideBookMarkService(@PuzzlingRetrofit retrofit: Retrofit): PersonalReviewApiService =
//        retrofit.create(PersonalReviewApiService::class.java)
}
