package com.puzzling.puzzlingaos.di

import com.puzzling.puzzlingaos.data.service.MyPageService
import com.puzzling.puzzlingaos.data.service.PersonalReviewService
import com.puzzling.puzzlingaos.data.service.ProjectService
import com.puzzling.puzzlingaos.data.service.TeamReviewService
import com.puzzling.puzzlingaos.data.service.WriteReviewService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideMyDashBoardService(@PuzzlingRetrofit retrofit: Retrofit): PersonalReviewService =
        retrofit.create(PersonalReviewService::class.java)

    @Provides
    @Singleton
    fun provideMyPageService(@PuzzlingRetrofit retrofit: Retrofit): MyPageService =
        retrofit.create(MyPageService::class.java)

    @Provides
    @Singleton
    fun provideProjectRegister(@PuzzlingRetrofit retrofit: Retrofit): ProjectService =
        retrofit.create(ProjectService::class.java)

    @Provides
    @Singleton
    fun provideTeamRetroList(@PuzzlingRetrofit retrofit: Retrofit): TeamReviewService =
        retrofit.create(TeamReviewService::class.java)

    @Provides
    @Singleton
    fun provideWriteReviewService(@PuzzlingRetrofit retrofit: Retrofit): WriteReviewService =
        retrofit.create(WriteReviewService::class.java)
}
