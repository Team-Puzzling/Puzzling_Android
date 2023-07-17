package com.puzzling.puzzlingaos

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.kakao.sdk.common.KakaoSdk
import com.puzzling.puzzlingaos.BuildConfig.NATIVE_APP_KEY
import com.puzzling.puzzlingaos.data.source.local.LocalDataSource
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, NATIVE_APP_KEY)
        LocalDataSource.init(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
