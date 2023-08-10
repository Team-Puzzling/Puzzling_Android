package com.puzzling.puzzlingaos.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.puzzling.puzzlingaos.data.datasource.local.LocalDataSource
import com.puzzling.puzzlingaos.data.service.KakaoLoginService
import com.puzzling.puzzlingaos.util.KakaoLoginCallback
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel(private val kakaoLoginService: KakaoLoginService) : ViewModel() {
    private val _isKakaoLogin = MutableStateFlow(false)
    val isKakaoLogin = _isKakaoLogin.asStateFlow()

    val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        KakaoLoginCallback {
            _isKakaoLogin.value = true
            Timber.d("토큰!!!! $token")
            LocalDataSource.setAccessToken("$token")
        }.handleResult(token, error)
    }

    fun kakaoLogin() = viewModelScope.launch {
        kakaoLoginService.startKakaoLogin(kakaoLoginCallback)
    }
}
