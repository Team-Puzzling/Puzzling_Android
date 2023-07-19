package com.puzzling.puzzlingaos.di

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.puzzling.puzzlingaos.BuildConfig
import com.puzzling.puzzlingaos.util.isJsonArray
import com.puzzling.puzzlingaos.util.isJsonObject
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val PUZZLING_BASE_URL = BuildConfig.PUZZLING_BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(tokenInterceptor())
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            when {
                message.isJsonObject() ->
                    Log.d("retrofit", JSONObject(message).toString(4))

                message.isJsonArray() ->
                    Log.d("retrofit", JSONArray(message).toString(4))

                else -> {
                    Log.d("retrofit", "CONNECTION INFO -> $message")
                }
            }
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    @PuzzlingRetrofit
    fun providePuzzlingRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(PUZZLING_BASE_URL)
        .client(okHttpClient)
        .build()

    private fun tokenInterceptor(): Interceptor {
        val requestInterceptor = Interceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
            builder.addHeader(
                "Authorization",
                "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODk2MTcyMTEsImV4cCI6MTY4OTk3NzIxMSwibWVtYmVySWQiOjJ9.w7II75HjzpXLVlBSRmcH5g5Q85oQ3KYq59KSlRvTV3w",
                // "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2ODk2MzAyMjIsImV4cCI6MTY4OTk5MDIyMiwibWVtYmVySWQiOjF9.tR938XSLko7fuv8RduU6AL7dFs8JnzDNRS1OIfpFSC0",
            )
            chain.proceed(builder.build())
        }
        return requestInterceptor
    }
}