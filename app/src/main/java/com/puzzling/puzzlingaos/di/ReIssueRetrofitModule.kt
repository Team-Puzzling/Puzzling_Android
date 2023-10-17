package com.puzzling.puzzlingaos.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.puzzling.puzzlingaos.BuildConfig
import com.puzzling.puzzlingaos.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReIssueRetrofitModule {
    private const val PUZZLING_BASE_URL = BuildConfig.PUZZLING_BASE_URL

    @Provides
    @Singleton
    @ReIssueRetrofit
    fun provideReIssueOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        @ReIssueRetrofit tokenInterceptor: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(tokenInterceptor)
            .build()

    @Singleton
    @Provides
    @ReIssueRetrofit
    fun provideReIssueTokenRetrofit(@ReIssueRetrofit okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(PUZZLING_BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    @ReIssueRetrofit
    fun reIssueTokenInterceptor(tokenRepository: TokenRepository): Interceptor {
        val requestInterceptor = Interceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
            val token = runBlocking {
                tokenRepository.getToken()
            }
            Log.d("ReIssueRetrofitModule", "토큰 $token")
            builder.addHeader(
                "Authorization",
                "Bearer ${token.accessToken}",
            ).addHeader(
                "Refresh",
                "Bearer ${token.refreshToken}",
            )
            chain.proceed(builder.build())
        }
        return requestInterceptor
    }
}
