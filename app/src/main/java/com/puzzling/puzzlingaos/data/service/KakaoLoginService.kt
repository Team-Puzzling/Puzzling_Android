package com.puzzling.puzzlingaos.data.service

import android.content.Context
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class KakaoLoginService(private val context: Context) {
    fun startKakaoLogin(kakaoLoginCallBack: (OAuthToken?, Throwable?) -> Unit) {
        val kakaoLoginState =
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
                KAKAO_APP_LOGIN
            } else {
                KAKAO_ACCOUNT_LOGIN
            }

        when (kakaoLoginState) {
            KAKAO_APP_LOGIN -> {
                UserApiClient.instance.loginWithKakaoTalk(
                    context,
                    callback = kakaoLoginCallBack,
                )
            }
            KAKAO_ACCOUNT_LOGIN -> {
                UserApiClient.instance.loginWithKakaoAccount(
                    context,
                    callback = kakaoLoginCallBack,
                )
            }
        }
    }

    fun kakaoLogout(kakaoLogoutCallBack: (Throwable?) -> Unit) {
        UserApiClient.instance.logout(kakaoLogoutCallBack)
    }

    // 탈퇴퇴
    fun kakaoDeleteAccount(kakaoLogoutCallBack: (Throwable?) -> Unit) {
        UserApiClient.instance.unlink(kakaoLogoutCallBack)
    }

    companion object {
        const val KAKAO_APP_LOGIN = 0
        const val KAKAO_ACCOUNT_LOGIN = 1
    }
}
