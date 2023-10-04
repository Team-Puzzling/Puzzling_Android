package com.puzzling.puzzlingaos.presentation.onboarding

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakao.sdk.auth.model.OAuthToken
import com.puzzling.puzzlingaos.data.datasource.local.LocalDataSource
import com.puzzling.puzzlingaos.domain.usecase.onboarding.GetTokenUseCase
import com.puzzling.puzzlingaos.domain.usecase.onboarding.PostTokenUseCase
import com.puzzling.puzzlingaos.util.KakaoLoginCallback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val postTokenUseCase: PostTokenUseCase,
    private val getTokenUseCase: GetTokenUseCase,
) :
    ViewModel() {
    private val _isKakaoLogin = MutableStateFlow(false)
    val isKakaoLogin = _isKakaoLogin.asStateFlow()

    val kakaoLoginCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        KakaoLoginCallback {
            _isKakaoLogin.value = true
            Log.d("LoginViewModel", "토큰!! $token")
            LocalDataSource.setAccessToken("$token")
            viewModelScope.launch {
                postTokenUseCase.invoke(it)
                Log.d("LoginViewModel", "토큰!! usecase ${getTokenUseCase.invoke()}")
            }
        }.handleResult(token, error)
    }
}
